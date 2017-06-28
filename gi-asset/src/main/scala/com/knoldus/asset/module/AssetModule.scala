package com.knoldus.asset.module

import com.google.inject.AbstractModule
import com.knoldus.persistence.asset.mappings.{AssetComponent, AssetPostgresComponent}
import com.knoldus.persistence.user.{UserComponent, UserComponentPostgres}
import net.codingwell.scalaguice.ScalaModule

// $COVERAGE-OFF$
class AssetModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {}
}
// $COVERAGE-ON$
