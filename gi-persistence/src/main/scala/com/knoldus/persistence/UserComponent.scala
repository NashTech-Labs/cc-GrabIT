package com.knoldus.persistence

import java.sql.Timestamp

import com.knoldus.persistence.mappings.UserMapping
import com.knoldus.utils.models.User

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object UserComponent extends UserMapping with DBDriver with GIDBComponent with App {
  this: DriverComponent =>

  import driver.api._

  /**
    * This method is used for insert user into database
    *
    * @param user
    * @return
    */
  def insert(user: User) = {
    val res = db.run(userInfo += user)
     Await.result(res, 2 seconds)
  }

  /**
    * This method is used for fetching user record with the help of email and password
    *
    * @param email
    * @param password
    * @return Option[User]
    */
  def getUserViaEmailAndPassword(email: String, password: String): Future[Option[User]] = {
    db.run(userInfo.filter(u => u.email === email && u.password === password).result.headOption)
  }

  /**
    * This method is used for fetching user record with the help of userId
    *
    * @param userId
    * @return Option[User]
    */
  def getUserViaUserId(userId: String): Future[Option[User]] = {
    db.run(userInfo.filter(u => u.id === userId).result.headOption)
  }

  /**
    * This method is used for fetching user record with the help of Access Token
    *
    * @param accessToken
    * @return Option[User]
    */
  def getUserViaAccessToken(accessToken: String): Future[Option[User]] = {
    db.run(userInfo.filter(u => u.accessToken === accessToken).result.headOption)
  }

  val time = new Timestamp(System.currentTimeMillis())
  val user: User = User("12", "jjjjjjjjj", "1024", "anurag", "anurag@knoldus.com", "anurag", "admin", time, time)
  insert(user)
  /*getUserViaAccessToken("jjjjjjjjj").map(s => println("!!!!!!!!!!!!!!!!!!!! a t " + s.get.email))
  getUserViaEmailAndPassword("anurag@knoldus.com","anurag").map(s => println("!!!!!!!!!!!!!!!!!!!! E P " + s.get.name))
  getUserViaUserId("12").map(s => println("!!!!!!!!!!!!!!!!!!!! User ID " + s.get.name))*/
}
