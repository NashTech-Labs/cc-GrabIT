package com.knoldus.persistence.components

import java.sql.Timestamp

import com.knoldus.persistence.db.H2DBComponent
import com.knoldus.utils.models.User
import org.scalatest.AsyncFunSuite

class UserComponentTest extends AsyncFunSuite with UserComponent with H2DBComponent {

  test("Insert user into database ") {
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val user = User("id-3", "acc-tok-3", "emp-id-3", "knol-joy", "anurag@knoldus.com", "knol-password3", "knol-fullStack3", dummyTimeStamp, dummyTimeStamp)
    val resultF = insert(user)
    resultF.map(res => assert(res === 1))
  }

  test("Fetch user record with the help of email and password") {
    val result = getUserByEmailAndPassword("sahil.sawhney@knoldus.in", "knol-password1")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-1", "acc-tok-1", "emp-id-1", "knol-sahil", "sahil.sawhney@knoldus.in", "knol-password1", "knol-fullStack1", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.get.id === expectedUser.id)
      assert(actualUser.get.accessToken === expectedUser.accessToken)
      assert(actualUser.get.employeeId === expectedUser.employeeId)
      assert(actualUser.get.name === expectedUser.name)
      assert(actualUser.get.email === expectedUser.email)
      assert(actualUser.get.password === expectedUser.password)
      assert(actualUser.get.role === expectedUser.role)
    }
  }

  test("Fetch user record with the help of userId") {
    val result = getUserByUserId("id-2")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-2", "acc-tok-2", "emp-id-2", "knol-joy", "jyotsana@knoldus.com", "knol-password2", "knol-fullStack2", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.get.id === expectedUser.id)
      assert(actualUser.get.accessToken === expectedUser.accessToken)
      assert(actualUser.get.employeeId === expectedUser.employeeId)
      assert(actualUser.get.name === expectedUser.name)
      assert(actualUser.get.email === expectedUser.email)
      assert(actualUser.get.password === expectedUser.password)
      assert(actualUser.get.role === expectedUser.role)
    }
  }

  test("Fetch user record with the help of Access Token") {
    val result = getUserByAccessToken("acc-tok-1")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-1", "acc-tok-1", "emp-id-1", "knol-sahil", "sahil.sawhney@knoldus.in", "knol-password1", "knol-fullStack1", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.get.id === expectedUser.id)
      assert(actualUser.get.accessToken === expectedUser.accessToken)
      assert(actualUser.get.employeeId === expectedUser.employeeId)
      assert(actualUser.get.name === expectedUser.name)
      assert(actualUser.get.email === expectedUser.email)
      assert(actualUser.get.password === expectedUser.password)
      assert(actualUser.get.role === expectedUser.role)
    }
  }

  test("Fetch all users") {
    val result = getAllUser
    result.map { actualUserList =>
      assert(actualUserList.length === 2)
    }
  }

}
