package com.knoldus.booking.model

import java.sql.Timestamp


case class BookingRequest(userId: String, assetId: String, actionPerformedBy: String, bookingDate : String, startTime: String,
                          endTime: String) {
  require(userId.trim.length > 0, "user Id should not be empty")
  require(assetId.trim.length > 0, "Asset Id should be in valid format")
  require(actionPerformedBy.trim.length > 0, "Action Performer name should not be empty")
  require(bookingDate.trim.length > 0, "Booking Date should not be empty")
  require(startTime.trim.length > 0, "Start time should not be empty")
  require(endTime.trim.length > 0, "End time should not be empty")
}
