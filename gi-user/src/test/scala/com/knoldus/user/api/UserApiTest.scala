package com.knoldus.user.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.user.service.UserService
import io.circe.generic.auto._
import io.circe.parser._
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
    val jsonString ="""{"empId":"1111","name":"test name","email":"test@gmail.com","role":"admin"}"""
    val body: JsValue = jsonString.parseJson
    val jsonFromString = parse(jsonString).right.get
    val user = jsonFromString.as[UserRegisterRequest].right.get

    val accessToken = Math.random().toString
    when(mockUserService.addUser(user)).thenReturn(Future(1))
    Post(
      s"/user/add?accessToken=$accessToken", body) ~> addUser ~>
      check {
        status shouldBe StatusCodes.OK
        responseAs[String] should include regex "Success"
      }
  }

  test("user Api route to sign in") {
    val jsonString ="""{"email":"test@gmail.com","password":"testpassword"}"""
    val body: JsValue = jsonString.parseJson
    val requestToken = Math.random()
    Post(s"/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "User logged in successfully"
    }
  }

  test("user Api route to sign in when empId or password is empty") {
    val jsonString ="""{"email":"test@gmail.com","password":""}"""
    val body: JsValue = jsonString.parseJson
    val requestToken = Math.random()
    Post(s"/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] shouldBe "Internal Server Error requirement failed: incomplete sign in details"
    }
  }

}
