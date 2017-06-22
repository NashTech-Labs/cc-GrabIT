package com.knoldus.user.module

import com.google.inject.AbstractModule
import com.knoldus.persistence.components.{UserComponent, UserComponentPostgres}
import net.codingwell.scalaguice.ScalaModule

/**
  * Created by jyotsna on 19/6/17.
  */
class UserModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserComponent].to[UserComponentPostgres]
  }
}
