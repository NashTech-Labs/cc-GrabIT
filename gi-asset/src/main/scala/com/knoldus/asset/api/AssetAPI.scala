package com.knoldus.asset.api

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.google.inject.Inject
import com.knoldus.asset.helper.AssetApiHelper
import com.knoldus.asset.model.AssetRequest
import com.knoldus.asset.service.AssetService
import io.circe.generic.auto._
import io.circe.parser._

import scala.util.{Failure, Success, Try}


class AssetAPI @Inject()(assetService: AssetService) extends AssetApiHelper {

  /**
    * Creates http route for add asset
    * @return
    */
  def addAsset: Route =
  cors() {
    path("asset" / "add") {
      (post & entity(as[String])) { data =>
        parameters("accessToken") { accessToken =>
          authorizeAsync(_ => assetService.isAdmin(accessToken)) {
            Try {
              decode[AssetRequest](data)
            } match {
              case Success(assetRequest) => assetRequest match {
                case Right(res) => handleAddAsset(res, assetService.insert)
                case Left(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"Body params are missing or incorrect: ${ex.getMessage}"))
              }
              case Failure(ex) => complete(HttpResponse(StatusCodes.BadRequest, entity = s"${ex.getMessage}".replace("requirement failed: ", "")))
            }
          }
        }
      }
    }
  }

  val routes = addAsset

}
