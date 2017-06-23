package com.knoldus.user.helper

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.user.helper.ValidationHelper._
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.utils.json.JsonHelper
import com.knoldus.utils.models.User
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.Future
import scala.util.{Failure, Success}

trait UserApiHelper extends JsonHelper {

  def handleSignIn(signInRequest: SignInRequest, signIn: (SignInRequest) => Future[Option[User]]): Route = {
    onComplete(signIn(signInRequest)) {
      case Success(Some(user)) => complete(user.asJson.toString)
      case Success(None) => complete(HttpResponse(StatusCodes.BadRequest, entity = "Invalid credentials"))
      case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
    }
  }

  def handleAddUser(userRegisterRequest: UserRegisterRequest, addUser: (UserRegisterRequest) => Future[Int]): Route = {
    val (isValid, message) = isUserRegisterRequestValid(userRegisterRequest)
    if (isValid) {
      onComplete(addUser(userRegisterRequest)) {
        case Success(res) => complete(HttpResponse(StatusCodes.OK, entity = s"User has been Successfully Added"))
        case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
      }
    } else {
      complete(HttpResponse(StatusCodes.BadRequest, entity = message))
    }
  }
}
