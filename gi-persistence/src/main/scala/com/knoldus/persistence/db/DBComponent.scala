package com.knoldus.persistence.db

import slick.driver.JdbcProfile


/**
  * Creating database component
  */
trait DBComponent {
  val driver: JdbcProfile

  import driver.api._

  val db: Database

}
