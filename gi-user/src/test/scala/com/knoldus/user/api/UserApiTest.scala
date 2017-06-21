package com.knoldus.user.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.knoldus.user.TestData._
import com.knoldus.user.model.SignInRequest
import com.knoldus.user.service.UserService
import com.knoldus.utils.models.User
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mock.MockitoSugar
import spray.json._
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class UserApiTest extends FunSuite with Matchers with ScalatestRouteTest with MockitoSugar {

  override def testConfigSource: String = "akka.loglevel = WARNING"

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  val mockUserService = mock[UserService]
  val userApi = new UserApi(mockUserService)

  import userApi._

  test("user Api route to add users") {
    val accessToken = Math.random().toString
    when(mockUserService.addUser(userFromJson)).thenReturn(Future(1))
    Post(
      s"/user/add?accessToken=$accessToken", body) ~> addUser ~>
      check {
        status shouldBe StatusCodes.OK
        responseAs[String] should include regex "Success"
      }
  }

  test("user Api route for failure case") {
    val accessToken = Math.random().toString
    when(mockUserService.addUser(userFromJson)).thenReturn(Future.failed(new IllegalArgumentException("Invalid user")))
    Post(
      s"/user/add?accessToken=$accessToken", body) ~> addUser ~>
      check {
        status shouldBe StatusCodes.InternalServerError
        responseAs[String] should include regex "Internal Server Error Invalid user"
      }
  }

  test("user Api route to sign in") {
    val jsonString ="""{"email":"test@gmail.com","password":"password"}"""
    val body: JsValue = jsonString.parseJson
    val signInRequest = SignInRequest(user.email, "password")
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.successful(Some(user.copy(password = "password"))))
    Post("/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex user.email
      responseAs[String] should include regex testUUID.toString
    }
  }

  test("user Api route to sign in when no user found") {
    val jsonString ="""{"email":"test@gmail.com","password":"password"}"""
    val body: JsValue = jsonString.parseJson
    val signInRequest = SignInRequest(user.email, "password")
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.successful(None))
    Post("/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] shouldBe "Invalid credentials"
    }
  }

  test("user Api route to sign in: Failure case") {
    val jsonString ="""{"email":"test@gmail.com","password":"password"}"""
    val body: JsValue = jsonString.parseJson
    val signInRequest = SignInRequest(user.email, "password")
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.failed(new RuntimeException))
    Post("/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("user Api route to fetch all users successfully") {
    when(mockUserService.getAllUsers).thenReturn(Future.successful(List(user)))
    Get("/user/get/all") ~> getAllUsers ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex user.email
      responseAs[String] should include regex user.name
    }
  }

  test("user Api route to fetch all users: Failure case") {
    when(mockUserService.getAllUsers).thenReturn(Future.failed(new RuntimeException))
    Get("/user/get/all") ~> getAllUsers ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

}
