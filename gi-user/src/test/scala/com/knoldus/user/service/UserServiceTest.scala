package com.knoldus.user.service

import com.knoldus.persistence.components.UserComponent
import com.knoldus.user.TestData._
import com.knoldus.user.model.SignInRequest
import com.knoldus.utils.models.User
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mock.MockitoSugar
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceTest extends FunSuite with Matchers with MockitoSugar with ScalaFutures {

  val mockUserComponent = mock[UserComponent]

  val userService = new UserService(mockUserComponent)

  test("add user functionality when user gets added successfully") {
    when(mockUserComponent.insert(any[User])).thenReturn(Future.successful(1))
    whenReady(userService.addUser(userRegisterRequest)) { result => result shouldBe 1 }
  }

  test("sign in functionality for successful sign in") {
    val signInRequest = SignInRequest("test@gmail.com", "password")
    when(mockUserComponent.getUserByEmailAndPassword(signInRequest.email, signInRequest.password)).thenReturn(Future(Some(user)))
    whenReady(userService.signIn(signInRequest)) { result => result shouldBe Some(user) }
  }

  test("sign in functionality for invalid creadentials") {
    val signInRequest = SignInRequest("test@gmail.com", "password")
    when(mockUserComponent.getUserByEmailAndPassword(signInRequest.email, signInRequest.password)).thenReturn(Future(Some(user)))
    whenReady(userService.signIn(signInRequest)) { result => result shouldBe Some(user) }
  }

  test("get all user functionality") {
    when(mockUserComponent.getAllUser).thenReturn(Future(List(user)))
    whenReady(userService.getAllUsers) { result => result shouldBe List(user)}
  }
}
