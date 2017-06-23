package com.knoldus.user.service

import com.google.inject.Inject
import com.knoldus.notify.email.EmailUtility
import com.knoldus.persistence.components.UserComponent
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.utils.PasswordHashing._
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.Constants._
import com.knoldus.utils.models.User

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import com.knoldus.user.Constants.Admin
import com.knoldus.utils.email.EmailTemplate._

class UserService @Inject()(userComponent: UserComponent, email: EmailUtility) {

  /**
    * Adds user object to the database
    *
    * @param userRegisterRequest
    * @return
    */
  def addUser(userRegisterRequest: UserRegisterRequest): Future[Int] = {
    val uuid = getUUID
    val currentTimestamp = getCurrentTimestamp
    val password = generateRandomString(PasswordLength)

    val message = addUserMessage(userRegisterRequest.name, userRegisterRequest.email, password)

    val user = User(uuid, uuid, userRegisterRequest.empId, userRegisterRequest.name, userRegisterRequest.email,
      generateHashedPassword(password), userRegisterRequest.role, currentTimestamp, currentTimestamp)

    for {
      insertedRecord <- userComponent.insert(user)
    } yield {
      Future(email.sendEmail(List(userRegisterRequest.email), addUserSubject, message))
      insertedRecord
    }
  }

  /**
    * Gets user details for sign in
    *
    * @param signInRequest
    * @return
    */
  def signIn(signInRequest: SignInRequest): Future[Option[User]] = {
    userComponent.getUserByEmail(signInRequest.email).map { userData =>
      userData.flatMap { user =>
        if (passwordMatches(signInRequest.password, user.password)) Some(user) else None
      }
    }
  }

  /**
    * Gets list of all users
    *
    * @return
    */
  def getAllUsers: Future[List[User]] = userComponent.getAllUser


  /**
    * Checks whether user is admin or not
    * @param accessToken
    * @return
    */
  def isAdmin(accessToken: String): Future[Boolean] = {
    userComponent.getUserByAccessToken(accessToken).map { user =>
      user.fold(false)(user => user.role == Admin)
    }
  }
}
