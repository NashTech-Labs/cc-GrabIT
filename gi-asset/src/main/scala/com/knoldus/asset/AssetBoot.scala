package com.knoldus.asset

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.google.inject.{Guice, Injector}
import com.knoldus.asset.api.AssetAPI
import com.knoldus.asset.module.AssetModule
import net.codingwell.scalaguice.InjectorExtensions._
import Constants._

object AssetBoot extends App {

  val injector: Injector = Guice.createInjector(new AssetModule)
  val assetApi = injector.instance[AssetAPI]

  implicit val actor = ActorSystem("asset-actor")
  implicit val materializer = ActorMaterializer()

  Http().bindAndHandle(assetApi.routes, "localhost", Port)
}
