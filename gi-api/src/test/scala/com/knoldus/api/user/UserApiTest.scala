package com.knoldus.api.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import org.scalatest._
import org.scalatest.mock.MockitoSugar
import spray.json._

import scala.concurrent.duration.DurationInt

class UserApiTest extends FunSuite with Matchers with ScalatestRouteTest with MockitoSugar with UserApi {

  override def testConfigSource = "akka.loglevel = WARNING"

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  test("user Api route to add users") {
    val jsonString ="""{"empId":"1111","name":"test name","email":"test@gmail.com","role":"admin"}"""
    val body: JsValue = jsonString.parseJson
    val requestToken = Math.random()
    Post(
      s"/add/user?requestToken=$requestToken", body) ~> addUser ~>
      check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex ("Success")
    }
  }

  test("user Api route to add users when request token is empty") {
    val jsonString ="""{"empId":"1111","name":"test name","email":"test@gmail.com","role":"admin"}"""
    val body: JsValue = jsonString.parseJson

    Post(
      "/add/user?requestToken=", body) ~> addUser ~>
      check {
        status shouldBe StatusCodes.InternalServerError
        responseAs[String] shouldBe "Internal Server Error requirement failed: request token is empty"
      }
  }

  test("user Api route to sign in") {
    val jsonString ="""{"email":"1111","password":"testpassword"}"""
    val body: JsValue = jsonString.parseJson
    val requestToken = Math.random()
    Post(s"/signin", body) ~> signIn ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldBe("User logged in successfully")
      }
  }

  test("user Api route to sign in when empId or password is empty") {
    val jsonString ="""{"email":"1111","password":""}"""
    val body: JsValue = jsonString.parseJson
    val requestToken = Math.random()
    Post(s"/signin", body) ~> signIn ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] shouldBe "Internal Server Error requirement failed: incomplete sign in details"
    }
  }

}
