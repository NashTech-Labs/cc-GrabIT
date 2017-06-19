package com.knoldus.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import spray.json.DefaultJsonProtocol

object JsonHelper extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val userRegisterRequestFormat = jsonFormat4(UserRegisterRequest)
  implicit val signinRequestFormat = jsonFormat2(SignInRequest)
}