package com.knoldus.utils

import java.sql.Timestamp
import java.util.UUID

class CommonUtility {

  /**
    * Generates random UUID
    * @return
    */
  def getUUID: UUID = UUID.randomUUID()

  /**
    * Get current timestamp
    * @return
    */
  def getCurrentTimestamp: Timestamp = new Timestamp(System.currentTimeMillis())
}
