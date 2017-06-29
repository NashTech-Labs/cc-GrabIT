package com.knoldus.asset.helper

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.knoldus.asset.model.AssetRequest

import scala.concurrent.Future
import scala.util.{Failure, Success}

trait AssetApiHelper {

  def handleAddAsset(assetRequest: AssetRequest, addAsset: (AssetRequest) => Future[Int]): Route = {
    onComplete(addAsset(assetRequest)) {
      case Success(res) => complete(HttpResponse(StatusCodes.OK, entity = s"Asset has been Successfully Added"))
      case Failure(ex) => complete(HttpResponse(StatusCodes.InternalServerError, entity = s"Internal Server Error ${ex.getMessage}"))
    }
  }
}
