package com.knoldus.notify.config

import com.knoldus.notify.EmailNotification
import pureconfig.error.ConfigReaderFailures
import pureconfig.loadConfig

object Configuration {

  def getConfig: Either[ConfigReaderFailures, EmailNotification] = loadConfig[EmailNotification]

}
