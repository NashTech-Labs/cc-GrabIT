package com.knoldus.notify.email

import com.knoldus.notify.EmailNotification
import com.knoldus.notify.config.Configuration
import com.typesafe.config.ConfigFactory.parseString
import org.scalatest.FunSuite
import pureconfig._

class EmailUtilityTest extends FunSuite {

  val emailUtility = new EmailUtility

   test("email sending functionality") {
     val message = "Hi User "
     val result = emailUtility.sendEmail(List("rishi1@knoldus.com"), message, "test")
     assert(result)
   }

  test("email sending failure due to config parsing failure") {
    val conf = parseString(s"""{ n: 1 }""")
    val emailUtility = new EmailUtility {
      override val emailConfig = loadConfig[EmailNotification](conf)
    }
    val message = "Hi User "
    val result = emailUtility.sendEmail(List("rishi@knoldusww.com"), message, "test")
    assert(!result)
  }

  test("email sending failure due to invalid email host") {
    val conf = Configuration.getConfig.right.get
    val emailUtility = new EmailUtility {
      override val emailConfig = Right(conf.copy(email = conf.email.copy(host = "invalid")))
    }
    val message = "Hi User "
    val result = emailUtility.sendEmail(List("rishi@knoldusww.com"), message, "test")
    assert(!result)
  }
}
