package com.knoldus.booking.service

import java.sql.Timestamp
import java.util.Calendar

import com.google.inject.Inject
import com.knoldus.booking.model.BookingRequest
import com.knoldus.persistence.booking.BookingComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.models.{Asset, Booking}
import com.knoldus.booking.Constants._
import com.knoldus.utils.CommonUtility._

import scala.concurrent.Future


class BookingService @Inject()(bookingComponent: BookingComponent) {

  /**
    * Add booking Object to database
    *
    * @param bookingRequest
    * @return
    */
  def addBooking(bookingRequest: BookingRequest): Future[Int] = {
    val bookingDate = addExtraHoursToTimestamp(new Timestamp(System.currentTimeMillis()), Five, Thirty)
    val startTime = addExtraHoursToTimestamp(Timestamp.valueOf(bookingRequest.startTime), Five, Thirty)
    val endTime = addExtraHoursToTimestamp(Timestamp.valueOf(bookingRequest.endTime), Five, Thirty)
    val bookingData = Booking(getUUID, bookingRequest.userId, bookingRequest.assetId, None, None, None, None, Booked, None,
      bookingDate, startTime, endTime, None)

    bookingComponent.insert(bookingData)
  }

  /**
    * Get list of assets available for booking
    * @param startTime
    * @param endTime
    * @param assetType
    * @return
    */
  def getAvailableAssets(startTime: String, endTime: String, assetType: String): Future[List[Asset]] = {
    val startTimeValue = Timestamp.valueOf(startTime)
    val endTimeValue = Timestamp.valueOf(endTime)
    bookingComponent.getAssetsAvailableForBooking(startTimeValue, endTimeValue, assetType)
  }

  /**
    * Get list of bookings by user id
    *
    * @param userId
    * @return
    */
  def getBookingsByUserId(userId: String): Future[List[Booking]] = {
    bookingComponent.getBookingsByUserId(userId)
  }

}
