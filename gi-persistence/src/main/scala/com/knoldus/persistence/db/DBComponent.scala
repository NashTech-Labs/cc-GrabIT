package com.knoldus.persistence.db

import com.knoldus.persistence.driver.DriverComponent


/**
 * Creating database component
 */
trait DBComponent {
  this: DriverComponent =>
  import driver.api._
  val db: Database

}
