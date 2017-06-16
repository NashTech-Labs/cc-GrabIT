package com.knoldus.register.services

import com.knoldus.register.models.UserRegisterRequest
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FunSuite, Matchers}
import scala.util.Success

class UserServiceTest extends FunSuite with Matchers with MockitoSugar with UserService {

  test("add user service") {
    val userRegisterRequest = UserRegisterRequest("1111", "user", "test@gmail.com", "admin")
    val result = addUser(userRegisterRequest)
    result shouldBe(Success(true))
  }

}
