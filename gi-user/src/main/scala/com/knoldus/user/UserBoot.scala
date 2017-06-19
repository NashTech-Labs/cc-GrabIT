package com.knoldus.user

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.google.inject.Guice
import com.knoldus.user.Constants.Port
import com.knoldus.user.api.UserApi
import com.knoldus.user.module.UserModule

object UserBoot extends App {

  val injector = Guice.createInjector(new UserModule)

  import net.codingwell.scalaguice.InjectorExtensions._
  val userApi = injector.instance[UserApi]

  implicit val system = ActorSystem("api-actor-system")
  implicit val materializer = ActorMaterializer()
  Http().bindAndHandle(userApi.routes, "localhost", Port)

}
