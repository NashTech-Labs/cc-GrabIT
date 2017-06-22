package com.knoldus.persistence.booking.mappings

import com.knoldus.persistence.PostgresDbComponent
import com.knoldus.utils.models.Booking
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class BookingComponent extends BookingMapping with PostgresDbComponent {

  import driver.api._

  /**
    * Insert booking into database
    *
    * @param booking
    * @return Future[Int]
    */

  def insert(booking: Booking): Future[Int] = {
    db.run(bookingInfo += booking)
  }

  /**
    * Get Booking details by userID
    *
    * @param userId
    * @return Future[List[Booking]]
    **/
  def getBookingByUserId(userId: String): Future[List[Booking]] = {
    db.run(bookingInfo.filter(booking => booking.userId === userId).to[List].result)
  }

  /**
    * Update Asset Feedback
    *
    * @param bookingId
    * @param assetFeedback
    * @param assetRating
    * @return
    */
  def updateAssetFeedbackDetails(bookingId: String, assetRating: Int, assetFeedback: String): Future[Int] = {
    db.run(bookingInfo.filter(bookingData => bookingData.id === bookingId)
      .map(value => (value.assetRating, value.assetFeedback))
      .update((assetRating, assetFeedback)))
  }

  /**
    * Update User Feedback
    *
    * @param bookingId
    * @param userRating
    * @param userFeedback
    * @return
    */
  def updateUserFeedbackDetails(bookingId: String, userRating: Int, userFeedback: String): Future[Int] = {
    db.run(bookingInfo.filter(bookingData => bookingData.id === bookingId)
      .map(value => (value.userRating, value.userFeedback))
      .update((userRating, userFeedback)))
  }
}
