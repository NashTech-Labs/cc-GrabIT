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

    def userRating: Rep[Option[Int]] = column[Option[Int]]("user_rating")

    def userFeedback: Rep[Option[String]] = column[Option[String]]("user_feedback")

    def assetRating: Rep[Option[Int]] = column[Option[Int]]("asset_rating")

    def assetFeedback: Rep[Option[String]] = column[Option[String]]("asset_feedback")

    def status: Rep[String] = column[String]("status")

    def actionPerformedBy: Rep[String] = column[String]("action_performed_by")

    def bookingDate: Rep[Timestamp] = column[Timestamp]("booking_date")

    def startTime: Rep[Timestamp] = column[Timestamp]("start_time")

    def endTime: Rep[Timestamp] = column[Timestamp]("end_time")

    def finishTime: Rep[Option[Timestamp]] = column[Option[Timestamp]]("finish_time")

    def * : ProvenShape[Booking] = (
      id,
      userId,
      assetId,
      userRating,
      userFeedback,
      assetRating,
      assetFeedback,
      status,
      actionPerformedBy,
      bookingDate,
      startTime,
      endTime,
      finishTime
    ) <> (Booking.tupled, Booking.unapply)
  }

  val bookingInfo: TableQuery[BookingMapping] = TableQuery[BookingMapping]
}
