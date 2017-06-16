package com.knoldus.register.services

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.persistence.UserComponent
import com.knoldus.register.models.{SignInRequest, UserRegisterRequest}
import com.knoldus.utils.models.User
import com.knoldus.utils.Constants._

import scala.concurrent.Future
import scala.util.Try

trait UserService extends UserComponent {

  /**
    *
    * @param userRegisterRequest
    * @return
    */
  def addUser(userRegisterRequest: UserRegisterRequest): Future[Int] = {
      val uuid = UUID.randomUUID().toString
      val currentTimestamp = new Timestamp(System.currentTimeMillis())
      val user = User(uuid, uuid, userRegisterRequest.empId, userRegisterRequest.name, userRegisterRequest.email,
        EmptyString, userRegisterRequest.role, currentTimestamp, currentTimestamp)
      insert(user)
  }
}
