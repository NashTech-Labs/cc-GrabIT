package com.knoldus.persistence.db

import slick.jdbc.PostgresProfile

trait PostgresDbComponent extends DBComponent {

  val driver = PostgresProfile

  import driver.api.Database

  val db: Database = DBConnection.connectionPool
}

object DBConnection {
  import slick.jdbc.PostgresProfile.api.Database
  val connectionPool = Database.forConfig("db")
}
