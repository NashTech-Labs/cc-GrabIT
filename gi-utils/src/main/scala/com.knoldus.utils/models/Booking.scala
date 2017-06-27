package com.knoldus.utils.models

import java.sql.Timestamp

case class Booking(
                    id: String,
                    userId: String,
                    assetId: String,
                    userRating: Int,
                    userFeedback: String,
                    assetRating: Int,
                    assetFeedback: String,
                    status: String,
                    actionPerformedBy: String,
                    bookingDate: Timestamp,
                    startTime: Timestamp,
                    endTime: Timestamp
                  )
