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

  val jsonString ="""{
                    |"empId":"1111",
                    |"name":"test name",
                    |"email":"test@gmail.com",
                    |"role":"admin"
                    |}""".stripMargin

  val jsonAST: JsValue = jsonString.parseJson


  test("user Api route to add users") {
    val requestToken = Math.random()
    Post(
      s"/add/user?requestToken=$requestToken", jsonAST) ~> addUser ~>
      check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex ("Success")
    }
  }

  test("user Api route to add users when request token is empty") {
    Post(
      "/add/user?requestToken=", jsonAST) ~> addUser ~>
      check {
        status shouldBe StatusCodes.InternalServerError
        responseAs[String] shouldBe "Internal Server Error requirement failed: request token is empty"
      }
  }
}
