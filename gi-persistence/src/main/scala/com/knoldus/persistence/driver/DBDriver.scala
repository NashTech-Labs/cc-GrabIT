package com.knoldus.persistence.driver

import slick.driver.{JdbcProfile, PostgresDriver}

trait DBDriver extends DriverComponent {

  val driver: JdbcProfile = PostgresDriver

}
