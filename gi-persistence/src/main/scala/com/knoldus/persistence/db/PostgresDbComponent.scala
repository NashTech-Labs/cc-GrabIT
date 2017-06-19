package com.knoldus.persistence.db

import slick.driver.{JdbcProfile, PostgresDriver}

trait PostgresDbComponent extends DBComponent {

  val driver: JdbcProfile = PostgresDriver

  import driver.api._

  val db: Database = DBConnection.connectionPool
}

object DBConnection {

  import slick.driver.PostgresDriver.api._

  val connectionPool = Database.forConfig("db")
}


