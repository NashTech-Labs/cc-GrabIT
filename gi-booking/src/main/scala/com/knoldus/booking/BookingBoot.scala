package com.knoldus.booking

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import com.google.inject.Guice
import com.knoldus.booking.Constants.Port
import com.knoldus.booking.api.BookingApi
import com.knoldus.booking.module.BookingModule
import net.codingwell.scalaguice.InjectorExtensions._


object BookingBoot extends App {
  val injector = Guice.createInjector(new BookingModule)

  val bookingApi = injector.instance[BookingApi]

  implicit val system = ActorSystem("booking-api-actor-system")
  implicit val materializer = ActorMaterializer()
  Http().bindAndHandle(bookingApi.routes, "localhost", Port)
}
