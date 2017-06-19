package com.knoldus.user.services

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.persistence.UserComponent
import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.user.service.UserService
import com.knoldus.utils.CommonUtility
import com.knoldus.utils.models.User
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceTest extends FunSuite with MockitoSugar {

  val mockCommonUtility = mock[CommonUtility]
  val mockUserComponent = mock[UserComponent]

  val userService = new UserService(mockCommonUtility, mockUserComponent)

  test("add user method") {
    val accessToken = "accessToken123"
    val testTimestamp = new Timestamp(1234)
    val testUUID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
    val userRegisterRequest = UserRegisterRequest("1111", "test name", "test@gmail.com", "admin")

    val user = User(testUUID.toString, testUUID.toString, userRegisterRequest.empId, userRegisterRequest.name,
      userRegisterRequest.email, "", userRegisterRequest.role, testTimestamp, testTimestamp)
    when(mockCommonUtility.getCurrentTimestamp).thenReturn(testTimestamp)
    when(mockCommonUtility.getUUID).thenReturn(testUUID)
    when(mockUserComponent.insert(user)).thenReturn(Future.successful(1))
    val futureResult = userService.addUser(accessToken, userRegisterRequest)
    futureResult.map(res => assert(res === 1))
  }
}
