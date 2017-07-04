package com.knoldus.user.service

import com.knoldus.notify.email.EmailUtility
import com.knoldus.persistence.user.UserComponent
import com.knoldus.user.TestData._
import com.knoldus.utils.models.User
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceTest extends AsyncFunSuite with Matchers with MockitoSugar {

  private val mockUserComponent = mock[UserComponent]
  private val mockEmailUtility = mock[EmailUtility]

  val userService = new UserService(mockUserComponent, mockEmailUtility)

  test("add user functionality when user gets added successfully") {
    when(mockUserComponent.insert(any[User])).thenReturn(Future.successful(1))
    when(mockEmailUtility.sendEmail(any[List[String]], any[String], any[String])).thenReturn(true)
    val output = userService.addUser(userRegisterRequest)
    output.map { result => result shouldBe 1 }
  }

  test("sign in functionality for successful sign in") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.successful(Some(user)))
    val output = userService.signIn(signInRequest)
    output.map { result => result shouldBe Some(user) }
  }

  test("sign in functionality for invalid password") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.successful(Some(user)))
    val output = userService.signIn(signInRequest.copy(password = "invalid1234"))
    output.map { result => result shouldBe None }
  }

  test("sign in functionality for invalid email") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.successful(None))
    val output = userService.signIn(signInRequest)
    output.map { result => result shouldBe None }
  }

  test("sign in functionality when exception occurs") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.failed(new RuntimeException))
    val output = userService.signIn(signInRequest)
    recoverToSucceededIf[Exception](output)
  }

  test("get all user functionality") {
    when(mockUserComponent.getAllUser).thenReturn(Future.successful(List(user)))
    val output = userService.getAllUsers
    output.map { result => result shouldBe List(user) }
  }

  test("whether user is admin successfully") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(Some(user)))
    val output = userService.isAdmin(accessToken)
    output.map(result => result shouldBe true)
  }

  test("whether user is admin if role is Employee") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(Some(user.copy(role = "Employee"))))
    val output = userService.isAdmin(accessToken)
    output.map(result => result shouldBe false)
  }

  test("whether user is admin if user is not present") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(None))
    val output = userService.isAdmin(accessToken)
    output.map(result => result shouldBe false)
  }

  test("whether user with email id is present") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.successful(Some(user)))
    val output = userService.isEmailExists(signInRequest.email)
    output.map(result => result shouldBe true)
  }


  test("whether user with email id is not present") {
    when(mockUserComponent.getUserByEmail(signInRequest.email)).thenReturn(Future.successful(None))
    val output = userService.isEmailExists(signInRequest.email)
    output.map(result => result shouldBe false)
  }


  test("whether user with employee id is present") {
    when(mockUserComponent.isEmployeeIdExists(userRegisterRequest.employeeId)).thenReturn(Future.successful(true))
    val output = userService.isEmployeeIdExists(userRegisterRequest.employeeId)
    output.map(result => result shouldBe true)
  }


  test("whether user with employee id is not present") {
    when(mockUserComponent.isEmployeeIdExists(userRegisterRequest.employeeId)).thenReturn(Future.successful(false))
    val output = userService.isEmployeeIdExists(userRegisterRequest.employeeId)
    output.map(result => result shouldBe false)
  }
}
