package com.knoldus.user

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.utils.PasswordHashing
import com.knoldus.utils.json.JsonHelper
import com.knoldus.utils.models.User
import io.circe.generic.auto._
import io.circe.syntax._

object TestData extends JsonHelper {
  val accessToken = "accessToken123"
  val testTimestamp = new Timestamp(123)
  val password = "test123"
  val testUUID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
  val hashPassword = PasswordHashing.generateHashedPassword(password)

  val userRegisterRequest = UserRegisterRequest("1111", "test name", "test@gmail.com", "admin")
  val userRegisterJson = userRegisterRequest.asJson.noSpaces
  val user = User("1", testUUID.toString, userRegisterRequest.employeeId, userRegisterRequest.name,
    userRegisterRequest.email, hashPassword, userRegisterRequest.role, testTimestamp, testTimestamp)

  val signInRequest = SignInRequest(user.email, password)
  val signInRequestJson = signInRequest.asJson.noSpaces

}
