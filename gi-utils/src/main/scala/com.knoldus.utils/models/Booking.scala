package com.knoldus.utils.models

import java.sql.Timestamp

case class Booking(
                    id: String,
                    userId: String,
                    assetId: String,
                    userRating: Option[Int],
                    userFeedback: Option[String],
                    assetRating: Option[Int],
                    assetFeedback: Option[String],
                    status: String,
                    actionPerformedBy: Option[String],
                    bookingDate: Timestamp,
                    startTime: Timestamp,
                    endTime: Timestamp,
                    finishTime: Option[Timestamp]
                  )
