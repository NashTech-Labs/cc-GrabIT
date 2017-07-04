package com.knoldus.user.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.AuthorizationFailedRejection
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.knoldus.utils.Constants.Employee
import com.knoldus.user.TestData._
import com.knoldus.user.service.UserService
import com.knoldus.utils.models.User
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class UserApiTest extends FunSuite with Matchers with ScalatestRouteTest with MockitoSugar {

  override def testConfigSource: String = "akka.loglevel = WARNING"

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  private val mockUserService = mock[UserService]
  val userApi = new UserApi(mockUserService)

  import userApi._

  test("user Api route to add users") {
    val accessToken = Math.random().toString
    when(mockUserService.addUser(userRegisterRequest)).thenReturn(Future.successful(1))
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex "Success"
    }
  }

  test("user Api route to add users when name is empty") {
    val accessToken = Math.random().toString
    val userRegisterJson = """{"employeeId":"1111","name":"","email":"test@gmail.com","role":"admin"}"""
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "Name should not be empty"
    }
  }

  test("user Api route to add users when email is in invalid format") {
    val accessToken = Math.random().toString
    val userRegisterJson = """{"employeeId":"1111","name":"test name","email":"invalid","role":"admin"}"""
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "Email should be in valid format"
    }
  }

  test("user Api route to add users when employee id is empty") {
    val accessToken = Math.random().toString
    val userRegisterJson = """{"employeeId":"","name":"test name","email":"test@gmail.com","role":"admin"}"""
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "Employee id should not be empty"
    }
  }

  test("user Api route to add users when role is invalid") {
    val accessToken = Math.random().toString
    val userRegisterJson = """{"employeeId":"1111","name":"test name","email":"test@gmail.com","role":"invalid"}"""
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "User role should be valid"
    }
  }

  test("user Api route to add users when user is not authorized") {
    val accessToken = Math.random().toString
    val userRegister = userRegisterRequest.copy(role = Employee)
    val userRegisterJson = userRegister.asJson.toString
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(false))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      rejection shouldEqual AuthorizationFailedRejection
    }
  }

  test("user Api route to add users when input json could not be parsed") {
    val accessToken = Math.random().toString
    val invalidJson = """{"invalid"}"""
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.addUser(userRegisterRequest)).thenReturn(Future.successful(1))
    Post(s"/user/add?accessToken=$accessToken", invalidJson) ~> addUser ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Body params are missing or incorrect"
    }
  }

  test("user Api route to add user for failure case") {
    val accessToken = Math.random().toString
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.addUser(userRegisterRequest)).thenReturn(Future.failed(new RuntimeException("Invalid user")))
    Post(s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error Invalid user"
    }
  }

  test("user Api route to sign in") {
    val userWithPassword = user.copy(password = "password")
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.successful(Some(userWithPassword)))
    Post("/signin", signInRequestJson) ~> signIn ~> check {
      status shouldBe StatusCodes.OK
      val response = decode[User](responseAs[String])
      response shouldBe Right(userWithPassword)
    }
  }

  test("user Api route to sign in when no user found") {
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.successful(None))
    Post("/signin", signInRequestJson) ~> signIn ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "Invalid credentials"
    }
  }

  test("user Api route to sign in when body params are invalid") {
    val invalidJson = """{"invalid"}"""
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.successful(None))
    Post("/signin", invalidJson) ~> signIn ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Body params are missing or incorrect"
    }
  }

  test("user Api route to sign in: Failure case") {
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.failed(new RuntimeException))
    Post("/signin", signInRequestJson) ~> signIn ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("user Api route to fetch all users successfully") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.getAllUsers).thenReturn(Future.successful(List(user)))
    Get(s"/user/get/all?accessToken=$accessToken") ~> getAllUsers ~> check {
      status shouldBe StatusCodes.OK
      val res = decode[List[User]](responseAs[String])
      res shouldBe Right(List(user))
    }
  }

  test("user Api route to fetch all users when user is not authorized") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(false))
    Get(s"/user/get/all?accessToken=$accessToken") ~> getAllUsers ~> check {
      rejection shouldBe AuthorizationFailedRejection
    }
  }

  test("user Api route to fetch all users: Failure case") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.getAllUsers).thenReturn(Future.failed(new RuntimeException))
    Get(s"/user/get/all?accessToken=$accessToken") ~> getAllUsers ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("user Api route to check whether user with email id exists successfully") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmailExists(signInRequest.email)).thenReturn(Future.successful(true))
    Get(s"/user/email/exists?email=${signInRequest.email}&accessToken=$accessToken") ~> checkUserWithEmail ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "true"
    }
  }

  test("user Api route to check whether user with email id does not exists") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmailExists(signInRequest.email)).thenReturn(Future.successful(false))
    Get(s"/user/email/exists?email=${signInRequest.email}&accessToken=$accessToken") ~> checkUserWithEmail ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "false"
    }
  }

  test("user Api route to check whether user with email id exists: Failure case") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmailExists(signInRequest.email)).thenReturn(Future.failed(new RuntimeException))
    Get(s"/user/email/exists?email=${signInRequest.email}&accessToken=$accessToken") ~> checkUserWithEmail ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("user Api route to check whether user with employee id exists successfully") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmployeeIdExists(userRegisterRequest.employeeId)).thenReturn(Future.successful(true))
    Get(s"/user/employeeid/exists?employeeId=${userRegisterRequest.employeeId}&accessToken=$accessToken") ~> checkUserWithEmployeeId ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "true"
    }
  }

  test("user Api route to check whether user with employee id does not exists") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmployeeIdExists(userRegisterRequest.employeeId)).thenReturn(Future.successful(false))
    Get(s"/user/employeeid/exists?employeeId=${userRegisterRequest.employeeId}&accessToken=$accessToken") ~> checkUserWithEmployeeId ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "false"
    }
  }

  test("user Api route to check whether user with employee id exists: Failure case") {
    when(mockUserService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockUserService.isEmployeeIdExists(userRegisterRequest.employeeId)).thenReturn(Future.failed(new RuntimeException))
    Get(s"/user/employeeid/exists?employeeId=${userRegisterRequest.employeeId}&accessToken=$accessToken") ~> checkUserWithEmployeeId ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }
}
