package com.knoldus.user.helper

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}

import scala.util.{Failure, Success, Try}

trait UserHelper {

  /**
    * Handles response for add user functionality
    *
    * @param requestToken
    * @param user
    * @return
    */
  def addUserHandler(requestToken: String, user: UserRegisterRequest): HttpResponse = {
    Try {
      require(requestToken.trim.nonEmpty, "request token is empty")
    } match {
      case Success(_) => HttpResponse(StatusCodes.OK, entity = s"Success $requestToken $user")
      case Failure(ex) => HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
    }
  }

  /**
    * Handles response for user sign in request
    *
    * @param signInRequest
    * @return
    */
  def signIn(signInRequest: SignInRequest): HttpResponse = {
    Try {
      require(signInRequest.email.trim.nonEmpty && signInRequest.password.trim.nonEmpty, "incomplete sign in details")
    } match {
      case Success(_) => HttpResponse(StatusCodes.OK, entity = s"User logged in successfully")
      case Failure(ex) => HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
    }
  }
}
