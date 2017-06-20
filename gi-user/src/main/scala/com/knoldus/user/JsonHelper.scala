package com.knoldus.user

import java.sql.Timestamp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.utils.models.User
import spray.json.{DefaultJsonProtocol, DeserializationException, JsNumber, JsValue, JsonFormat}

object JsonHelper extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val timestamp = new  JsonFormat[Timestamp] {
    def write(obj: Timestamp) = JsNumber(obj.getTime)

    def read(json: JsValue) = json match {
      case JsNumber(time) => new Timestamp(time.toLong)
      case _ => throw new DeserializationException("Date expected")
    }
  }
  implicit val userRegisterRequestFormat = jsonFormat4(UserRegisterRequest)
  implicit val userFormat = jsonFormat9(User)
  implicit val signinRequestFormat = jsonFormat2(SignInRequest)

}
