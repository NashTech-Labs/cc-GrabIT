package com.knoldus.persistence.db

import com.knoldus.persistence.driver.DriverComponent
import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by jyotsna on 16/6/17.
  */
trait GIDBComponent extends DBComponent {

  this: DriverComponent =>
  import driver.api._

  private val runMode = scala.util.Properties.envOrElse("runMode", "application").toLowerCase().trim
  private val config = ConfigFactory.load().withFallback(ConfigFactory.load().getConfig("application"))
  val poolName = "giDBConf"
  val pool = Database.forConfig(poolName, config)
  val db: Database = pool

}
