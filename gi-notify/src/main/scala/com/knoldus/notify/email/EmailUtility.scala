package com.knoldus.notify.email

import java.util.Properties

import com.knoldus.notify.{EmailInfo, EmailNotification}
import com.knoldus.notify.config.Configuration._

import scala.util.{Failure, Success, Try}
import javax.mail.Address
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

import pureconfig.error.ConfigReaderFailures

class EmailUtility {

  val emailConfig: Either[ConfigReaderFailures, EmailNotification] = getConfig

  def sendEmail(recipients: List[String], subject: String, message: String): Boolean = {

    emailConfig match {
      case Left(ex) => false

      case Right(config) => Try {
        val emailInfo = config.email
        val properties = getProperties(config.email)
        val session = Session.getDefaultInstance(properties)
        val msg = new MimeMessage(session)
        val recipientAddress: Array[Address] = (recipients map { recipient => new InternetAddress(recipient) }).toArray
        msg.setFrom(new InternetAddress(emailInfo.user))
        msg.addRecipients(Message.RecipientType.TO, recipientAddress)
        msg.setSubject(subject)
        msg.setContent(message, "text/html")
        val transport = session.getTransport(emailInfo.protocol)
        transport.connect(emailInfo.host, emailInfo.user, emailInfo.password)
        transport.sendMessage(msg, msg.getAllRecipients)
      } match {
        case Success(_) => true
        case Failure(ex) => false
      }
    }
  }

  private def getProperties(emailInfo: EmailInfo) = {
    val props = new Properties
    props.put("mail.smtp.port", emailInfo.smtp.port.toString)
    props.setProperty("mail.transport.protocol", emailInfo.protocol)
    props.setProperty("mail.smtp.starttls.enable", emailInfo.smtp.starttlsEnable.toString)
    props.setProperty("mail.host", emailInfo.host)
    props.setProperty("mail.user", emailInfo.user)
    props.setProperty("mail.password", emailInfo.password)
    props
  }

}

