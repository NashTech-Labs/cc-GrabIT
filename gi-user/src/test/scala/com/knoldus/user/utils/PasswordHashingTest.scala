package com.knoldus.user.utils

import org.scalatest.FunSuite

class PasswordHashingTest extends FunSuite {

  test("One should be able to generate password hash from password") {
    val password ="test1234"
    val hashPwd = PasswordHashing.generateHashedPassword(password)
    assert(PasswordHashing.passwordMatches(password, hashPwd))
  }

}
