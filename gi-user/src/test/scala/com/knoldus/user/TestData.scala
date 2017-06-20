package com.knoldus.user

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.utils.models.User

object TestData {
  val testTimestamp = new Timestamp(1234)
  val testUUID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
  val userRegisterRequest = UserRegisterRequest("1111", "test name", "test@gmail.com", "admin")

  val user = User(testUUID.toString, testUUID.toString, userRegisterRequest.empId, userRegisterRequest.name,
    userRegisterRequest.email, "", userRegisterRequest.role, testTimestamp, testTimestamp)
}
