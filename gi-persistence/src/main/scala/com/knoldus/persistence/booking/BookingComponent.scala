package com.knoldus.persistence.booking.mappings

import com.knoldus.persistence.PostgresDbComponent
import com.knoldus.utils.models.Booking

import scala.concurrent.Future

class BookingComponent extends BookingMapping with PostgresDbComponent {

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
  def getBookingByUserId(userId: String): Future[List[Booking]] = {
    db.run(bookingInfo.filter(booking => booking.userId === userId).to[List].result)
  }

  /**
    * Update Booking Details
    *
    * @param booking
    * @return Future[Int]
    */
  def updateBookingDetails(booking: Booking): Future[Int] = {
    db.run(bookingInfo.filter(bookingData => bookingData.id === booking.id).update(booking))
  }

}
