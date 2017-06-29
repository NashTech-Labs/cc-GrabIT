package com.knoldus.booking.module

import com.google.inject.AbstractModule
import com.knoldus.persistence.booking.{BookingComponent, BookingPOstgresComponent}
import net.codingwell.scalaguice.ScalaModule

// $COVERAGE-OFF$
class BookingModule extends AbstractModule with ScalaModule {
override def configure(): Unit = {
    bind[BookingComponent].to[BookingPOstgresComponent]
  }
}
// $COVERAGE-ON$
