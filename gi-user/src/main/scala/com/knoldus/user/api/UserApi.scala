package com.knoldus.user.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.google.inject.Inject
import com.knoldus.user.JsonHelper._
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.service.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import scala.util.control.NonFatal

class UserApi @Inject()(userService: UserService ) {

  /**
    * Creates http route for add user
    * @return
    */
  def addUser: Route =
  path("user" / "add") {
    (post & entity(as[UserRegisterRequest])) { userRegisterRequest =>
      parameters("accessToken") { accessToken =>
        complete(userService.addUser(userRegisterRequest) map { response =>
          HttpResponse(StatusCodes.OK, entity = s"User has been Successfully Added")
        } recover {
          case NonFatal(ex) =>
            HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
        })
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
      complete(Try {
        require(signInRequest.email.trim.nonEmpty && signInRequest.password.trim.nonEmpty, "incomplete sign in details")
      } match {
        case Success(_) => HttpResponse(StatusCodes.OK, entity = s"User logged in successfully")
        case Failure(ex) => HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
      })
    }
  }

  val routes = addUser ~ signIn
}
