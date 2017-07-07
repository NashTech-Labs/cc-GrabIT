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
    val result = getBookingByUserId("user-id-1")
    result.map { bookings =>
      assert(bookings.length === 1)
      assert(bookings.head.id === "id-1")
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
      assert(updatedRowCount === 0)
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
      assert(updatedRowCount === 0)
    }
  }
}
