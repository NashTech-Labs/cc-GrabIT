package com.knoldus.api.user

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import com.knoldus.register.models.UserRegisterRequest

trait UserHelper {

  def addUserHandler(requestToken: String, user: UserRegisterRequest): HttpResponse = {
    HttpResponse(StatusCodes.OK, entity = s"Success $requestToken $user")
  }
  
}
