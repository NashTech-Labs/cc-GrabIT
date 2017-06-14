package com.knoldus.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.api.user.UserApi

object GIBoot extends App with UserApi {
  implicit val system = ActorSystem("api-actor-system")
  implicit val materializer = ActorMaterializer()
  Http().bindAndHandle(addUser, "localhost", 9999)
}
