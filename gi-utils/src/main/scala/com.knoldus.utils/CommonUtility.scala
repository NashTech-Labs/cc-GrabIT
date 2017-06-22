package com.knoldus.utils

import java.sql.Timestamp
import java.util.UUID
import scala.util.Random

object CommonUtility {

  /**
    * Generates random UUID
    *
    * @return
    */
  def getUUID: String = UUID.randomUUID().toString

  /**
    * Get current timestamp
    *
    * @return
    */
  def getCurrentTimestamp: Timestamp = new Timestamp(System.currentTimeMillis())

  /**
    * Generates random string of length size
    *
    * @param length
    * @return
    */
  def generateRandomString(length: Int): String = {
    Random.alphanumeric.take(length).toList.mkString
  }
}
