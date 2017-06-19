package com.knoldus.user.helper

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.service.UserService

import scala.concurrent.Future
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

trait UserHelper extends UserService {

  /**
    * Handles response for add user functionality
    *
    * @param requestToken
    * @param user
    * @return
    */
  def addUserHandler(requestToken: String, user: UserRegisterRequest): Future[HttpResponse] = {
    addUser(user) map { response =>
      HttpResponse(StatusCodes.OK, entity = s"User has been Successfully Added")
    } recover {
      case NonFatal(ex) => HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
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
