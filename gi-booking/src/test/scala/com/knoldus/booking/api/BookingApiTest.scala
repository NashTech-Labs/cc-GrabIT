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

  val bookingRequest = BookingRequest("123", "a-123", "2017-06-26 18:48:05.123", "2017-06-27 18:48:05.123", "2017-06-27 17:48:05.123")

  test("booking Api route to add booking") {
    when(mockBookingService.addBooking(bookingRequest)).thenReturn(Future.successful(1))
    Post(s"/booking/add", bookingRequest.asJson.noSpaces) ~> addBooking ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] should include regex "Success"
    }
  }

  test("booking Api route to add booking when userId empty") {
    val bookingRequestJson =
      """{"userId":"","assetId":"a-123","bookingDate":"2017-06-26 18:48:05.123",
        |"startTime":"2017-06-27 18:48:05.123","endTime":"2017-06-27 17:48:05.123"}""".stripMargin
    Post(s"/booking/add", bookingRequestJson) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "User Id should not be empty"
    }
  }

  test("booking Api route to add booking when assetId empty") {
    val bookingRequestJson =
      """{"userId":"123","assetId":"","bookingDate":"2017-06-26 18:48:05.123",
        |"startTime":"2017-06-27 18:48:05.123","endTime":"2017-06-27 17:48:05.123"}""".stripMargin
    Post(s"/booking/add", bookingRequestJson) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Asset Id should not be empty"
    }
  }

  test("booking Api route to add booking when bookingDate empty") {
    val bookingRequestJson =
      """{"userId":"123","assetId":"a-123","bookingDate":"",
        |"startTime":"2017-06-27 18:48:05.123","endTime":"2017-06-27 17:48:05.123"}""".stripMargin
    Post(s"/booking/add", bookingRequestJson) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Booking Date should not be empty"
    }
  }

  test("booking Api route to add booking when startTime empty") {
    val bookingRequestJson =
      """{"userId":"123","assetId":"a-123","bookingDate":"2017-06-26 18:48:05.123",
        |"startTime":"","endTime":"2017-06-27 17:48:05.123"}""".stripMargin
    Post(s"/booking/add", bookingRequestJson) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Start Time should not be empty"
    }
  }

  test("booking Api route to add booking when endTime empty") {
    val bookingRequestJson =
      """{"userId":"123","assetId":"a-123","bookingDate":"2017-06-26 18:48:05.123",
        |"startTime":"2017-06-27 18:48:05.123","endTime":""}""".stripMargin
    Post(s"/booking/add", bookingRequestJson) ~> addBooking ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "End Time should not be empty"
    }
  }
}
