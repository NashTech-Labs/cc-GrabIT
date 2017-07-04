package com.knoldus.persistence.user

import java.sql.Timestamp

import com.knoldus.persistence.db.H2DBComponent
import com.knoldus.utils.models.User
import org.h2.jdbc.JdbcSQLException
import org.scalatest.AsyncFunSuite

class UserComponentTest extends AsyncFunSuite with UserComponent with H2DBComponent {

  test("Insert user into database ") {
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val user = User("id-3", "acc-tok-3", "emp-id-3", "knol-joy", "anurag@knoldus.com", "knol-password3", "knol-fullStack3", dummyTimeStamp, dummyTimeStamp)
    val resultF = insert(user)
    resultF.map(res => assert(res === 1))
  }

  test("Insert user into database with existing primary key") {
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val user = User("id-2", "acc-tok-3", "emp-id-3", "knol-joy", "anurag@knoldus.com", "knol-password3", "knol-fullStack3", dummyTimeStamp, dummyTimeStamp)
    recoverToSucceededIf[JdbcSQLException](insert(user))
  }

  test("Fetch user record with the help of email ") {
    val result = getUserByEmail("sahil.sawhney@knoldus.in")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-1", "acc-tok-1", "emp-id-1", "knol-sahil", "sahil.sawhney@knoldus.in", "knol-password1", "knol-fullStack1", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.map(_.id) === Some(expectedUser.id))
      assert(actualUser.map(_.accessToken) === Some(expectedUser.accessToken))
      assert(actualUser.map(_.email) === Some(expectedUser.email))
    }
  }

  test("Fetch user record with the wrong value of email") {
    val result = getUserByEmail("wrong.email@knoldus.com")
    result.map { actualUser =>
      assert(actualUser === None)
    }
  }

  test("Fetch user record with the help of userId") {
    val result = getUserByUserId("id-2")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-2", "acc-tok-2", "emp-id-2", "knol-joy", "jyotsana@knoldus.com", "knol-password2", "knol-fullStack2", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.map(_.id) === Some(expectedUser.id))
      assert(actualUser.map(_.accessToken) === Some(expectedUser.accessToken))
      assert(actualUser.map(_.email) === Some(expectedUser.email))
    }
  }

  test("Fetch user record with the wrong userId") {
    val result = getUserByUserId("wrong-id")
    result.map { actualUser =>
      assert(actualUser === None)
    }
  }

  test("Fetch user record with the help of Access Token") {
    val result = getUserByAccessToken("acc-tok-1")
    val dummyTimeStamp = new Timestamp(System.currentTimeMillis())
    val expectedUser = User(
      "id-1", "acc-tok-1", "emp-id-1", "knol-sahil", "sahil.sawhney@knoldus.in", "knol-password1", "knol-fullStack1", dummyTimeStamp, dummyTimeStamp
    )
    result.map { actualUser =>
      assert(actualUser.map(_.id) === Some(expectedUser.id))
      assert(actualUser.map(_.accessToken) === Some(expectedUser.accessToken))
      assert(actualUser.map(_.email) === Some(expectedUser.email))
    }
  }

  test("Fetch user record with wrong Access Token") {
    val result = getUserByAccessToken("wrong-acc-tok")
    result.map { actualUser =>
      assert(actualUser === None)
    }
  }

  test("Fetch all users") {
    val result = getAllUser
    result.map { actualUserList =>
      assert(actualUserList.length === 2)
    }
  }

  test("check user by employee Id") {
    val result = isEmployeeIdExists("emp-id-1")
    result.map(isExists => assert(isExists === true))
  }

  test("check user by employee Id for wrong employee id") {
    val result = isEmployeeIdExists("wrong emp id")
    result.map(isExists => assert(isExists === false))
  }

}
