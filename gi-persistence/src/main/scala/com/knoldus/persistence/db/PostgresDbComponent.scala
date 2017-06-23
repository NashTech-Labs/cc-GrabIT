package com.knoldus.persistence.db

import slick.jdbc.PostgresProfile

// $COVERAGE-OFF
trait PostgresDbComponent extends DBComponent {

  val driver = PostgresProfile

  import driver.api.Database

  val db: Database = DBConnection.connectionPool
}
// $COVERAGE-ON

// $COVERAGE-OFF
object DBConnection {
  import slick.jdbc.PostgresProfile.api.Database
  val connectionPool = Database.forConfig("db")
}
// $COVERAGE-ON
