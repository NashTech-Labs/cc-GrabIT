package com.knoldus.user.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.knoldus.user.model.SignInRequest
import com.knoldus.user.service.UserService
import com.knoldus.utils.models.User
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mock.MockitoSugar
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import com.knoldus.user.TestData._

class UserApiTest extends FunSuite with Matchers with ScalatestRouteTest with MockitoSugar {

  override def testConfigSource: String = "akka.loglevel = WARNING"

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  val mockUserService = mock[UserService]
  val userApi = new UserApi(mockUserService)

  import userApi._

  test("user Api route to add users") {
    val accessToken = Math.random().toString
    when(mockUserService.addUser(userRegisterRequest)).thenReturn(Future(1))
    Post(
      s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~>
      check {
        status shouldBe StatusCodes.OK
        responseAs[String] should include regex "Success"
      }
  }

  test("user Api route for failure case") {
    val accessToken = Math.random().toString
    when(mockUserService.addUser(userRegisterRequest)).thenReturn(Future.failed(new RuntimeException("Invalid user")))
    Post(
      s"/user/add?accessToken=$accessToken", userRegisterJson) ~> addUser ~>
      check {
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

  test("user Api route to sign in: Failure case") {
    when(mockUserService.signIn(signInRequest)).thenReturn(Future.failed(new RuntimeException))
    Post("/signin", signInRequestJson) ~> signIn ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("user Api route to fetch all users successfully") {
    when(mockUserService.getAllUsers).thenReturn(Future.successful(List(user)))
    Get("/user/get/all") ~> getAllUsers ~> check {
      status shouldBe StatusCodes.OK
      val res = decode[List[User]](responseAs[String])
      res shouldBe Right(List(user))
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
