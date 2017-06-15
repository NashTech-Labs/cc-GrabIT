package com.knoldus.api.user

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import com.knoldus.register.models.UserRegisterRequest

import scala.util.{Failure, Success, Try}

trait UserHelper {

  /**
    * Handles response for add user functionality
    * @param requestToken
    * @param user
    * @return
    */
  def addUserHandler(requestToken: String, user: UserRegisterRequest): HttpResponse = {
    Try {
      require(requestToken.trim.nonEmpty, "request token is empty")
    } match {
      case Success(_) =>  HttpResponse(StatusCodes.OK, entity = s"Success $requestToken $user")
      case Failure(ex) => HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}")
    }
  }
}
