package com.knoldus.user

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.user.Constants.Port
import com.knoldus.user.api.UserApi

object UserBoot extends UserApi {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("api-actor-system")
    implicit val materializer = ActorMaterializer()
    Http().bindAndHandle(addUser, "localhost", Port)
  }

}
