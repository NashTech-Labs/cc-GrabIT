package com.knoldus.user.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.register.models.{SignInRequest, UserRegisterRequest}
import com.knoldus.user.JsonSupport._
import com.knoldus.user.helper.UserHelper

class UserApi extends UserHelper {

  /**
    * Creates http route for add user
    * @return
    */
  def addUser: Route =
    path("add" / "user") {
      (post & entity(as[UserRegisterRequest])) { userRegisterRequest =>
        parameters("requestToken") { requestToken =>
          complete(addUserHandler(requestToken, userRegisterRequest))
        }
      }
    }

  /**
    * Creates http route for sign in
    * @return
    */
  def signIn: Route =
  path("signin") {
    (post & entity(as[SignInRequest])) { signInRequest =>
        complete(signIn(signInRequest))
    }
  }
}
