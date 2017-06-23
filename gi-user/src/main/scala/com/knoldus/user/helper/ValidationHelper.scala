package com.knoldus.user.helper

import com.knoldus.user.model.UserRegisterRequest
import com.knoldus.user.Constants._

object ValidationHelper {

  def isUserRegisterRequestValid(userRegisterRequest: UserRegisterRequest): (Boolean, String) = {
    if (userRegisterRequest.name.length == 0) {
      (false, "Name should not be empty")
    } else if (!isEmailValid(userRegisterRequest.email)) {
      (false, "Email should be in valid format")
    } else if (userRegisterRequest.empId.length == 0) {
      (false, "Employee id should not be empty")
    } else if (!List(Admin, Employee).contains(userRegisterRequest.role)) {
      (false, "User role should be valid")
    } else {
      (true, "Valid user register request")
    }
  }

  private def isEmailValid(email: String): Boolean = {
    val emailPattern = """^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$""".r
    emailPattern findFirstIn email.toCharArray match {
      case Some(_) => true
      case None => false
    }
  }
}
