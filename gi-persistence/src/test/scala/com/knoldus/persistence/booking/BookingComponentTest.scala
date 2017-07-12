package com.knoldus.persistence.booking

import java.sql.Timestamp

import com.knoldus.persistence.db.H2DBComponent
import com.knoldus.utils.models.Booking
import org.h2.jdbc.JdbcSQLException
import org.scalatest.AsyncFunSuite

class BookingComponentTest extends AsyncFunSuite with BookingComponent with H2DBComponent {

  test("Insert booking record in database successfully") {
    val timestamp = new Timestamp(123)
    val booking = Booking("id-2", "user-id-1", "asset-id-1", Some(4), Some("user rating"), Some(3), Some("asset rating"), "booked", Some("user-id-2"),
      timestamp, timestamp, timestamp, Some(timestamp))
    val result = insert(booking)
    result.map(count => assert(count === 1))
  }

  test("Insert booking record in database when primary key already exists") {
    val timestamp = new Timestamp(123)
    val booking = Booking("id-1", "user-id-1", "asset-id-1", Some(4), Some("user rating"),
      Some(3), Some("asset rating"), "booked", Some("user-id-2"),
      timestamp, timestamp, timestamp, Some(timestamp))
    val result = insert(booking)
    recoverToSucceededIf[JdbcSQLException](result)
  }

  test("Get booking record from database successfully") {
    val result = getBookingsByUserId("user-id-1")
    result.map { bookings =>
      assert(bookings.length === 3)
      assert(bookings.head._1.id === "id-1")
      assert(bookings.head._2.id === "asset-id-2")
    }
  }

  test("Update asset feedback detail in booking record successfully") {
    val result = updateAssetFeedbackDetails("id-1", Some(3), Some("good"))
    result.map { updatedRowCount =>
      assert(updatedRowCount === 1)
    }
  }

  test("Update asset feedback detail in booking record for wrong booking id") {
    val result = updateAssetFeedbackDetails("id-3", Some(3), Some("good"))
    result.map { updatedRowCount =>
      assert(updatedRowCount === 1)
    }
  }


  test("Update user feedback detail in booking record successfully") {
    val result = updateUserFeedbackDetails("id-1", Some(2), Some("irresponsible"))
    result.map { updatedRowCount =>
      assert(updatedRowCount === 1)
    }
  }

  test("Update user feedback detail in booking record for wrong booking id") {
    val result = updateUserFeedbackDetails("id-3", Some(3), Some("good"))
    result.map { updatedRowCount =>
      assert(updatedRowCount === 1)
    }
  }
  test("get available assets for booking, when exact booking slot already booked") {
    val startTime = Timestamp.valueOf("2017-07-08 11:00:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 12:00:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 2)
    }
  }

  test("get available assets for booking, when booking slot overlaps") {
    val startTime = Timestamp.valueOf("2017-07-08 11:30:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 12:30:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 2)
    }
  }

  test("get available assets for booking, when booking slot overlaps start time less than booked time") {
    val startTime = Timestamp.valueOf("2017-07-08 10:30:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 11:30:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 2)
    }
  }

  test("get available assets for booking, when booking slot overlaps end time greater than booked time") {
    val startTime = Timestamp.valueOf("2017-07-08 10:00:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 13:00:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 2)
    }
  }

  test("get available assets for booking, when booking slot doesn't overlaps") {
    val startTime = Timestamp.valueOf("2017-07-08 08:00:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 09:00:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 3)
    }
  }

  test("get available assets for booking, when asset type is wrong") {
    val startTime = Timestamp.valueOf("2017-07-08 08:00:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 09:00:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "wrong type")
    result.map { assets =>
      assert(assets.length === 0)
    }
  }

  test("get available assets for booking, when booking slot overlaps but status is not booked") {
    val startTime = Timestamp.valueOf("2017-07-08 14:00:00.0")
    val endTime = Timestamp.valueOf("2017-07-08 15:00:00.0")
    val result = getAssetsAvailableForBooking(startTime, endTime, "projector")
    result.map { assets =>
      assert(assets.length === 3)
    }
  }

}
