package com.knoldus.user.service

import com.google.inject.Inject
import com.knoldus.persistence.UserComponent
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.Constants._
import com.knoldus.utils.models.User

import scala.concurrent.Future

class UserService @Inject()(userComponent: UserComponent) {

  /**
    * Service to add user object to the database
    * @param userRegisterRequest
    * @return
    */
  def addUser(userRegisterRequest: UserRegisterRequest): Future[Int] = {
    val uuid = getUUID
    val currentTimestamp = getCurrentTimestamp
    val user = User(uuid, uuid, userRegisterRequest.empId, userRegisterRequest.name, userRegisterRequest.email,
      EmptyString, userRegisterRequest.role, currentTimestamp, currentTimestamp)
    userComponent.insert(user)
  }

  /**
    * Service to get user details for sign in
    * @param signInRequest
    * @return
    */
  def signIn(signInRequest: SignInRequest): Future[Option[User]] = {
    userComponent.getUserByEmailAndPassword(signInRequest.email, signInRequest.password)
  }

  /**
    * Service to get all users
    * @return
    */
  def getAllUsers: Future[List[User]] = userComponent.getAllUser

}
