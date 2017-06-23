package com.knoldus.user.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.google.inject.Inject
import com.knoldus.user.helper.UserApiHelper
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.service.UserService
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.util.{Failure, Success, Try}


class UserApi @Inject()(userService: UserService ) extends UserApiHelper {

  /**
    * Creates http route for add user
    * @return
    */
  def addUser: Route =
  path("user" / "add") {
    (post & entity(as[String])) { data =>
      parameters("accessToken") { accessToken =>
        authorizeAsync(_ => userService.isAdmin(accessToken)) {
          Try {
            decode[UserRegisterRequest](data)
          } match {
            case Success(decodedUserRequest) => decodedUserRequest match {
              case Right(userRegisterRequest) => handleAddUser(userRegisterRequest, userService.addUser)
              case Left(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"Body params are missing or incorrect: ${ex.getMessage}"))
            }
            case Failure(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"${ex.getMessage}".replace("requirement failed: ", "")))
          }
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
    (post & entity(as[String])) { data =>
      val decodedSignInRequest = decode[SignInRequest](data)

      decodedSignInRequest match {
        case Right(signInRequest) => handleSignIn(signInRequest, userService.signIn)
        case Left(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"${ex.getMessage}"))
      }
    }
  }

  /**
    * Creates http route to get list of all users
    * @return
    */
  def getAllUsers: Route =
  path("user" / "get" / "all") {
    get {
      parameters("accessToken") { accessToken =>
        authorizeAsync(_ => userService.isAdmin(accessToken)) {
          onComplete(userService.getAllUsers) {
            case Success(users) => complete(HttpResponse(StatusCodes.OK, entity = users.asJson.toString))
            case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
          }
        }
      }
    }
  }

  val routes = addUser ~ signIn ~ getAllUsers
}
