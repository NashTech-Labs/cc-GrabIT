package com.knoldus.utils.models

import java.sql.Timestamp

case class Booking(
                    id: String,
                    userId: String,
                    assetId: String,
                    startTime: Timestamp,
                    endTime: Timestamp,
                    bookingDate: Timestamp,
                    userRating: Int,
                    userFeedback: String,
                    assetRating: Int,
                    assetFeedback: String,
                    status: String,
                    actionPerformedBy: String
                  )
