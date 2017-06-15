package com.knoldus.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.register.models.{SignInRequest, UserRegisterRequest}
import spray.json.DefaultJsonProtocol

object JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val userRegisterRequestFormat = jsonFormat4(UserRegisterRequest)
  implicit val signinRequestFormat = jsonFormat2(SignInRequest)
}
