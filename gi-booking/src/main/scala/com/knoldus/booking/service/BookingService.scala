package com.knoldus.booking.service

import java.sql.Timestamp
import java.util.Calendar

import com.google.inject.Inject
import com.knoldus.booking.model.BookingRequest
import com.knoldus.persistence.booking.BookingComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.models.{Asset, Booking}
import com.knoldus.booking.Constants.{BOOKED, ADMIN}
import com.knoldus.persistence.user.UserComponent
import scala.concurrent.ExecutionContext.Implicits.global


import scala.concurrent.Future


class BookingService @Inject()(bookingComponent: BookingComponent, userComponent: UserComponent) {

  /**
    * Add booking Object to database
    *
    * @param bookingRequest
    * @return
    */
  def addBooking(bookingRequest: BookingRequest): Future[Int] = {

    val bookingDate = new Timestamp(System.currentTimeMillis())
    val bookingData = Booking(getUUID, bookingRequest.userId, bookingRequest.assetId, None, None, None, None, BOOKED, None,
      bookingDate, Timestamp.valueOf(bookingRequest.startTime), Timestamp.valueOf(bookingRequest.endTime), None)

    bookingComponent.insert(bookingData)
  }

  /**
    * Get list of assets available for booking
    *
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
    * Get list of booking
    *
    * @return
    */
  def getAllBooking(): Future[List[Booking]] = {
    bookingComponent.getAllBooking
  }

  def isAdmin(userId: String): Future[Boolean] = {
    userComponent.getUserByUserId(userId).map { user =>
      user.fold(false)(user => user.role == ADMIN)
    }
  }
}
