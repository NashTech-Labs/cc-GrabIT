package com.knoldus.booking.model

case class BookingRequest(userId: String, assetId: String, bookingDate: String, startTime: String,
                          endTime: String) {
  require(userId.trim.length > 0, "User Id should not be empty")
  require(assetId.trim.length > 0, "Asset Id should not be empty")
  require(bookingDate.trim.length > 0, "Booking Date should not be empty")
  require(startTime.trim.length > 0, "Start Time should not be empty")
  require(endTime.trim.length > 0, "End Time should not be empty")
}
