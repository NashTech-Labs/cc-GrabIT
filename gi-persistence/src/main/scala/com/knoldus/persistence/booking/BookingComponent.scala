package com.knoldus.persistence.booking

import com.google.inject.ImplementedBy
import com.knoldus.persistence.PostgresDbComponent
import com.knoldus.persistence.asset.mappings.AssetMapping
import com.knoldus.persistence.booking.mappings.BookingMapping
import com.knoldus.persistence.db.DBComponent
import com.knoldus.utils.models.{Asset, Booking}

import scala.concurrent.Future

@ImplementedBy(classOf[BookingPostgresComponent])
trait BookingComponent extends BookingMapping with AssetMapping {

  this: DBComponent =>

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
  def getBookingsByUserId(userId: String): Future[List[(Booking, Asset)]] = {
    val query = bookingInfo.filter(booking => booking.userId === userId) join assetInfo on {
      case (bi, ai) => bi.assetId === ai.id
    } map {
      case (bi, ai) => (bi, ai)
    }
    db.run(query.to[List].result)
  }

  /**
    * Update Asset Feedback
    *
    * @param bookingId
    * @param assetFeedback
    * @param assetRating
    * @return
    */
  def updateAssetFeedbackDetails(bookingId: String, assetRating: Option[Int], assetFeedback: Option[String]): Future[Int] = {
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
  def updateUserFeedbackDetails(bookingId: String, userRating: Option[Int], userFeedback: Option[String]): Future[Int] = {
    db.run(bookingInfo.filter(bookingData => bookingData.id === bookingId)
      .map(value => (value.userRating, value.userFeedback))
      .update((userRating, userFeedback)))
  }
}

class BookingPostgresComponent extends BookingComponent with PostgresDbComponent
