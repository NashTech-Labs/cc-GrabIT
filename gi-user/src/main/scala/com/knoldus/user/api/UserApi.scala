package com.knoldus.user.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.google.inject.Inject
import com.knoldus.user.JsonHelper._
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.service.UserService

import scala.util.{Failure, Success}

class UserApi @Inject()(userService: UserService ) {

  /**
    * Creates http route for add user
    * @return
    */
  def addUser: Route =
  path("user" / "add") {
    (post & entity(as[UserRegisterRequest])) { userRegisterRequest =>
      parameters("accessToken") { accessToken =>
        onComplete(userService.addUser(userRegisterRequest)) {
          case Success(res) => complete(HttpResponse(StatusCodes.OK, entity = s"User has been Successfully Added"))
          case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
        }
      }
    }
  }

  /**
    * Creates http route for sign in
    * @return
    */
  def signIn: Route =
  path("signin") {
    (post & entity(as[SignInRequest])) { signInRequest =>
      onComplete(userService.signIn(signInRequest)) {
        case Success(Some(user)) => complete(user)
        case Success(None) => complete(HttpResponse(StatusCodes.BadRequest, entity = "Invalid credentials"))
        case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
      }
    }
  }

  val routes = addUser ~ signIn
}
