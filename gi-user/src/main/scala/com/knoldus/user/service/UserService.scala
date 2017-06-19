package com.knoldus.user.service

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.persistence.UserComponent
import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.utils.Constants._
import com.knoldus.utils.models.User

import scala.concurrent.Future

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
