package com.knoldus.api.user

import akka.http.scaladsl.model.{StatusCodes, _}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.register.models.UserRegisterRequest

trait UserApi extends UserHelper {

  def addUser: Route =
    path("add" / "user") {
      (post & entity(as[UserRegisterRequest])) { userRegisterRequest =>
        parameters("requestToken") { requestToken =>
          complete(addUserHandler(requestToken, userRegisterRequest))
        }
      }
    }
}
