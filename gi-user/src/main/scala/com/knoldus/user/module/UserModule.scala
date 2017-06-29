package com.knoldus.user.module

import com.google.inject.AbstractModule
import com.knoldus.persistence.user.{UserComponent, UserPostgresComponent}
import net.codingwell.scalaguice.ScalaModule

// $COVERAGE-OFF$
class UserModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserComponent].to[UserPostgresComponent]
  }
}
// $COVERAGE-ON$
