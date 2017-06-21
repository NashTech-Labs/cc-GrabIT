package com.knoldus.user

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.utils.models.User
import spray.json.JsValue
import io.circe.generic.auto._
import io.circe.parser._
import spray.json._

object TestData {
  val accessToken = "accessToken123"
  val testTimestamp = new Timestamp(1234)
  val testUUID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
  val userRegisterRequest = UserRegisterRequest("1111", "test name", "test@gmail.com", "admin")

  val user = User("1", testUUID.toString, userRegisterRequest.empId, userRegisterRequest.name,
    userRegisterRequest.email, "", userRegisterRequest.role, testTimestamp, testTimestamp)

  val jsonString ="""{"empId":"1111","name":"test name","email":"test@gmail.com","role":"admin"}"""
  val body: JsValue = jsonString.parseJson
  val jsonFromString = parse(jsonString).right.get
  val userFromJson = jsonFromString.as[UserRegisterRequest].right.get
}
