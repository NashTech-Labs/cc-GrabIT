package com.knoldus.user.model

import com.knoldus.user.Constants._

case class UserRegisterRequest(employeeId: String, name: String, email: String, role: String) {

    require(name.trim.length > 0, "Name should not be empty")
    require(isEmailValid(email.trim), "Email should be in valid format")
    require(employeeId.trim.length > 0, "Employee id should not be empty")
    require(List(Admin, Employee).contains(role), "User role should be valid")

  private def isEmailValid(email: String): Boolean = {
    EmailPattern.r findFirstIn email.toCharArray match {
      case Some(_) => true
      case None => false
    }
  }

}

case class SignInRequest(email: String, password: String)
