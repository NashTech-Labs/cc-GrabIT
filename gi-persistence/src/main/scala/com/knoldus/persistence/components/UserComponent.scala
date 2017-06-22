package com.knoldus.persistence.components

import com.knoldus.persistence.mappings.UserMapping
import com.knoldus.persistence.{DBComponent, PostgresDbComponent}
import com.knoldus.utils.models.User

import scala.concurrent.Future

trait UserComponent extends UserMapping {

  this: DBComponent =>

  import driver.api._

  /**
    * This method is used for insert user into database
    *
    * @param user
    * @return
    */
  def insert(user: User): Future[Int] = {
    db.run(userInfo += user)
  }

  /**
    * Fetches user detail using email
    *
    * @param email
    * @return Future[Option[User]]
    **/
  def getUserByEmail(email: String): Future[Option[User]] = {
    db.run(userInfo.filter(user => user.email === email).result.headOption)
  }

  /**
    * This method is used for fetching user record with the help of userId
    *
    * @param userId
    * @return Option[User]
    */
  def getUserByUserId(userId: String): Future[Option[User]] = {
    db.run(userInfo.filter(user => user.id === userId).result.headOption)
  }

  /**
    * This method is used for fetching user record with the help of Access Token
    *
    * @param accessToken
    * @return Option[User]
    */
  def getUserByAccessToken(accessToken: String): Future[Option[User]] = {
    db.run(userInfo.filter(user => user.accessToken === accessToken).result.headOption)
  }

  /**
    * This method is used for fetching All user from DB
    *
    * @return
    */
  def getAllUser: Future[List[User]] = {
    db.run(userInfo.to[List].result)
  }
}

class UserComponentPostgres extends UserComponent with PostgresDbComponent
