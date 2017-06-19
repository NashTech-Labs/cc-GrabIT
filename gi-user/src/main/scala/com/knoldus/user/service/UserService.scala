package com.knoldus.user.service

import com.google.inject.Inject
import com.knoldus.persistence.UserComponent
import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.utils.CommonUtility
import com.knoldus.utils.Constants._
import com.knoldus.utils.models.User

import scala.concurrent.Future

class UserService @Inject()(commonUtility: CommonUtility, userComponent: UserComponent) {

  /**
    *
    * @param userRegisterRequest
    * @return
    */
  def addUser(userRegisterRequest: UserRegisterRequest): Future[Int] = {
    val uuid = commonUtility.getUUID.toString
    val currentTimestamp = commonUtility.getCurrentTimestamp
    val user = User(uuid, uuid, userRegisterRequest.empId, userRegisterRequest.name, userRegisterRequest.email,
      EmptyString, userRegisterRequest.role, currentTimestamp, currentTimestamp)
    userComponent.insert(user)
  }
}
