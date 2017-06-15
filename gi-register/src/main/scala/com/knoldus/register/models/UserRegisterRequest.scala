package com.knoldus.register.models

case class UserRegisterRequest(empId: String, name: String, email: String, role: String)
case class SignInRequest(email: String, password: String)
