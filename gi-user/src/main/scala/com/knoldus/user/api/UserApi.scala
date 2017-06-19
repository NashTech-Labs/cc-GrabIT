package com.knoldus.user.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.user.JsonHelper._
import com.knoldus.user.helper.UserHelper
import com.knoldus.user.model.{SignInRequest, UserRegisterRequest}

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

  val userRoutes = addUser ~ signIn
}
