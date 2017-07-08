package com.knoldus.booking.service

import java.sql.Timestamp

import com.google.inject.Inject
import com.knoldus.booking.Constants.{ADMIN, _}
import com.knoldus.booking.model.BookingRequest
import com.knoldus.persistence.booking.BookingComponent
import com.knoldus.persistence.user.UserComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.models.{Asset, Booking}

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
    val bookingDate = addExtraHoursToTimestamp(new Timestamp(System.currentTimeMillis()), Five, Thirty)
    val startTime = addExtraHoursToTimestamp(Timestamp.valueOf(bookingRequest.startTime), Five, Thirty)
    val endTime = addExtraHoursToTimestamp(Timestamp.valueOf(bookingRequest.endTime), Five, Thirty)
    val bookingData = Booking(getUUID, bookingRequest.userId, bookingRequest.assetId, None, None, None, None, Booked, None,
      bookingDate, startTime, endTime, None)

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

  /** Get list of bookings by user id
    *
    * @param userId
    * @return
    */
  def getBookingsByUserId(userId: String): Future[List[(Booking, Asset)]] = {
    bookingComponent.getBookingsByUserId(userId)
  }

}
