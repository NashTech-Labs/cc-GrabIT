package com.knoldus.user.service

import com.knoldus.persistence.UserComponent
import com.knoldus.user.TestData._
import com.knoldus.user.model.SignInRequest
import com.knoldus.utils.CommonUtility
import org.mockito.Mockito._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceTest extends FunSuite with Matchers with MockitoSugar with ScalaFutures {

  val mockCommonUtility = mock[CommonUtility]
  val mockUserComponent = mock[UserComponent]

  val userService = new UserService(mockCommonUtility, mockUserComponent)

  test("add user method") {
    when(mockCommonUtility.getCurrentTimestamp).thenReturn(testTimestamp)
    when(mockCommonUtility.getUUID).thenReturn(testUUID)
    when(mockUserComponent.insert(user)).thenReturn(Future.successful(1))

    whenReady(userService.addUser(userRegisterRequest)) { result => result shouldBe 1 }
  }

  test("sign in method") {
    val signInRequest = SignInRequest("test@gmail.com", "password")

    when(mockUserComponent.getUserByEmailAndPassword(signInRequest.email, signInRequest.password)).thenReturn(Future(Some(user)))
    whenReady(userService.signIn(signInRequest)) { result => result shouldBe Some(user) }
  }
}
