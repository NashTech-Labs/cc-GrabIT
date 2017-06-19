package com.knoldus.persistence.driver

import slick.driver.JdbcProfile


/**
 * A profile for accessing SQL databases via JDBC. All drivers for JDBC-based databases
 * implement this profile.
 */
trait DriverComponent {
  val driver: JdbcProfile
}
