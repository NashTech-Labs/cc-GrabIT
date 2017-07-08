package com.knoldus.booking.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.google.inject.Inject
import com.knoldus.booking.helper.BookingApiHelper
import com.knoldus.booking.model.BookingRequest
import com.knoldus.booking.service.BookingService
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.util.{Failure, Success, Try}

class BookingApi @Inject()(bookingService: BookingService) extends BookingApiHelper {

  /**
    * Creates http route for add booking
    *
    * @return
    */
  def addBooking: Route =
    cors() {
      path("booking" / "add") {
        (post & entity(as[String])) { data =>
          Try {
            decode[BookingRequest](data)
          } match {
            case Success(decodedBookingRequest) => decodedBookingRequest match {
              case Right(bookingRegisterRequest) => handleAddBooking(bookingRegisterRequest, bookingService.addBooking)
              case Left(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"Body params are missing or incorrect: ${ex.getMessage}"))
            }
            case Failure(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"${ex.getMessage}".replace("requirement failed: ", "")))
          }
        }
      }
    }

  /**
    * Creates http route to get list of bookings by user id
    *
    * @return
    */
  def getBookingsByUserId: Route = cors() {
    path("bookings") {
      get {
        parameters("userId") { userId =>
          onComplete(bookingService.getBookingsByUserId(userId)) {
            case Success(bookings) => complete(HttpResponse(status = StatusCodes.OK, entity = bookings.asJson.toString))
            case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
          }
        }
      }
    }
  }

  val routes = addBooking ~ getBookingsByUserId
}
