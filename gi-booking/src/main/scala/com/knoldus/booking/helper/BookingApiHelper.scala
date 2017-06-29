package com.knoldus.booking.helper


import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.knoldus.booking.model.BookingRequest
import com.knoldus.utils.json.JsonHelper

import scala.concurrent.Future
import scala.util.{Failure, Success}

trait BookingApiHelper extends JsonHelper{

def handleAddBooking(bookingRequest: BookingRequest, addBooking: (BookingRequest) => Future[Int]): Route = {
      onComplete(addBooking(bookingRequest)) {
        case Success(res) => complete(HttpResponse(StatusCodes.OK, entity = s"Booking has been Successfully Added"))
        case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
      }
  }
}
