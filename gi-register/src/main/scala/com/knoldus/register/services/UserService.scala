package com.knoldus.register.services

import java.util.UUID

import com.knoldus.register.models.{SignInRequest, UserRegisterRequest}
import com.knoldus.utils.models.User
import com.knoldus.utils.Constants._

import scala.util.Try

trait UserService {

  def addUser(userRegisterRequest: UserRegisterRequest): Try[Boolean] = {
    Try {
      val uuid = UUID.randomUUID().toString
      User(uuid, uuid, userRegisterRequest.empId, userRegisterRequest.name, userRegisterRequest.email, EmptyString, userRegisterRequest.role)
      true
    }
  }
}
