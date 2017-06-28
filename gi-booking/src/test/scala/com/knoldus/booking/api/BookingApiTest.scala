package com.knoldus.booking.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.knoldus.booking.model.BookingRequest
import com.knoldus.booking.service.BookingService
import org.mockito.Mockito.when
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import io.circe.generic.auto._
import io.circe.syntax._

class BookingApiTest extends FunSuite with Matchers with ScalatestRouteTest with MockitoSugar {

  override def testConfigSource: String = "akka.loglevel = WARNING"

  implicit val routeTestTimeout = RouteTestTimeout(10 seconds)

  private val mockBookingService = mock[BookingService]
  val bookingApi = new BookingApi(mockBookingService)

  import bookingApi._

  val bookingRequest = BookingRequest("123", "a-123", "anurag", "2017-06-26 18:48:05.123", "2017-06-27 18:48:05.123", "2017-06-27 17:48:05.123")
  val bookingRequest1 = BookingRequest("123", "a-123", "", "2017-06-26 18:48:05.123", "2017-06-27 18:48:05.123", "2017-06-27 17:48:05.123")
  test("booking Api route to add booking") {
    when(mockBookingService.addBooking(bookingRequest)).thenReturn(Future.successful(1))
    Post(s"/booking/add", bookingRequest.asJson.noSpaces) ~> addBooking ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex "Success"
    }
  }

/*  test("booking Api route to add booking when action perform by empty") {
   // when(mockBookingService.addBooking(bookingRequest)).thenReturn(Future.successful(0))
    Post(s"/booking/add", bookingRequest1.asJson.noSpaces) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should contain "Action Performer name should not be empty"
    }
  }*/
}
