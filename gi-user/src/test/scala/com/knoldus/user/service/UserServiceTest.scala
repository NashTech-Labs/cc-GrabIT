package com.knoldus.user.service

import java.sql.Timestamp
import java.util.UUID

import com.knoldus.persistence.UserComponent
import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.utils.CommonUtility
import com.knoldus.utils.models.User
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mock.MockitoSugar

import scala.concurrent.Future

class UserServiceTest extends FunSuite with MockitoSugar with ScalaFutures {

  val mockCommonUtility = mock[CommonUtility]
  val mockUserComponent = mock[UserComponent]

  val userService = new UserService(mockCommonUtility, mockUserComponent)

  test("add user method") {
    val testTimestamp = new Timestamp(1234)
    val testUUID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")
    val userRegisterRequest = UserRegisterRequest("1111", "test name", "test@gmail.com", "admin")

    val user = User(testUUID.toString, testUUID.toString, userRegisterRequest.empId, userRegisterRequest.name,
      userRegisterRequest.email, "", userRegisterRequest.role, testTimestamp, testTimestamp)
    when(mockCommonUtility.getCurrentTimestamp).thenReturn(testTimestamp)
    when(mockCommonUtility.getUUID).thenReturn(testUUID)
    when(mockUserComponent.insert(user)).thenReturn(Future.successful(1))

    whenReady(userService.addUser(userRegisterRequest)){result => assert(result === 1)}
  }
}
