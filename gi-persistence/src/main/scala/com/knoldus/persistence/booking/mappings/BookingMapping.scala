package com.knoldus.persistence.booking.mappings

import java.sql.Timestamp

import com.knoldus.persistence._
import com.knoldus.utils.models.Booking


trait BookingMapping {
  this: DBComponent =>

  import driver.api._

  class BookingMapping(tag: Tag) extends Table[Booking](tag, "booking") {
    def id: Rep[String] = column[String]("id", O.PrimaryKey)

    def userId: Rep[String] = column[String]("user_id")

    def assetId: Rep[String] = column[String]("asset_id")

    def startTime: Rep[Timestamp] = column[Timestamp]("start_time")

    def endTime: Rep[Timestamp] = column[Timestamp]("end_time")

    def bookingDate: Rep[Timestamp] = column[Timestamp]("booking_date")

    def userRating: Rep[Int] = column[Int]("user_rating")

    def userFeedback: Rep[String] = column[String]("user_feedback")

    def assetRating: Rep[Int] = column[Int]("asset_rating")

    def assetFeedback: Rep[String] = column[String]("asset_feedback")

    def status: Rep[String] = column[String]("status")

    def actionPerformedBy: Rep[String] = column[String]("action_performed_by")

    def * : ProvenShape[Booking] = (
      id,
      userId,
      assetId,
      startTime,
      endTime,
      bookingDate,
      userRating,
      userFeedback,
      assetRating,
      assetFeedback,
      status,
      actionPerformedBy
    ) <> (Booking.tupled, Booking.unapply)
  }

  val bookingInfo: TableQuery[BookingMapping] = TableQuery[BookingMapping]
}
