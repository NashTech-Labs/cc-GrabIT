package com.knoldus.booking.service

import java.sql.Timestamp

import com.knoldus.booking.model.BookingRequest
import com.knoldus.persistence.booking.BookingComponent
import com.knoldus.utils.models.{Asset, Booking}
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class BookingServiceTest extends AsyncFunSuite with Matchers with MockitoSugar {

  private val mockBookingComponent = mock[BookingComponent]

  val bookingService = new BookingService(mockBookingComponent)

  val bookingRequest = BookingRequest("123", "a-123", "2017-06-26 18:48:05.123", "2017-06-27 18:48:05.123", "2017-06-27 17:48:05.123")
  test("add booking functionality when booking gets added successfully") {

    when(mockBookingComponent.insert(any[Booking])).thenReturn(Future.successful(1))
    val output = bookingService.addBooking(bookingRequest)
    output.map { result => result shouldBe 1 }
  }

  test("get list of bookings by user id") {
    val timestamp = new Timestamp(123)
    val booking = Booking("id-123", "user-123", "asset-123", None, None, None, None, "booked",
      None, timestamp, timestamp, timestamp, None)
    val asset = Asset("asset-123", "projector1", "projector1", "projector", true, timestamp, timestamp)
    when(mockBookingComponent.getBookingsByUserId("user-123")).thenReturn(Future.successful(List((booking, asset))))
    val output = bookingService.getBookingsByUserId("user-123")
    output.map { bookings => bookings shouldBe List((booking, asset))}
  }

}
