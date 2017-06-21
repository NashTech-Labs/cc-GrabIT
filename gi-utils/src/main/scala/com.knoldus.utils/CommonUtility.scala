package com.knoldus.utils

import java.sql.Timestamp
import java.util.UUID

object CommonUtility {

  /**
    * Generates random UUID
    * @return
    */
  def getUUID: String = UUID.randomUUID().toString

  /**
    * Get current timestamp
    * @return
    */
  def getCurrentTimestamp: Timestamp = new Timestamp(System.currentTimeMillis())
}
