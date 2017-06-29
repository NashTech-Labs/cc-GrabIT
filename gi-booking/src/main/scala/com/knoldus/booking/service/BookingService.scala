package com.knoldus.booking.service

import java.sql.Timestamp

import com.google.inject.Inject
import com.knoldus.booking.model.BookingRequest
import com.knoldus.persistence.booking.BookingComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.models.Booking
import com.knoldus.booking.Constants.BOOKED

import scala.concurrent.Future


class BookingService @Inject()(bookingComponent: BookingComponent) {

  /**
    * Add booking Object to database
    *
    * @param bookingRequest
    * @return
    */
  def addBooking(bookingRequest: BookingRequest): Future[Int] = {

    val bookingData = Booking(getUUID, bookingRequest.userId, bookingRequest.assetId, None, None, None, None, BOOKED, None,
      Timestamp.valueOf(bookingRequest.bookingDate), Timestamp.valueOf(bookingRequest.startTime), Timestamp.valueOf(bookingRequest.endTime), None)

    bookingComponent.insert(bookingData)
  }

}
