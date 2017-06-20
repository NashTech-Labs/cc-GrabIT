package com.knoldus

package object notify {

  case class EmailInfo(user: String, password: String, host: String, protocol: String, smtp: SmtpInfo)

  case class SmtpInfo(port: Int, starttlsEnable: Boolean)

  case class EmailNotification(email: EmailInfo)

}
