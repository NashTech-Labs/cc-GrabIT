package com.knoldus.asset

import java.sql.Timestamp

import com.knoldus.asset.model.AssetRequest
import com.knoldus.utils.json.JsonHelper
import com.knoldus.utils.models.{Asset, User}
import io.circe.generic.auto._
import io.circe.syntax._

object TestData extends JsonHelper {
  val accessToken = "accessToken123"
  val testTimestamp = new Timestamp(123)

  val user = User("1", accessToken, "1111", "test name", "test@gmail.com", "password", "admin", testTimestamp, testTimestamp)

  val assetRequest = AssetRequest("projector 1", "projectorUnique1", "projector")
  val assetRequestJson = assetRequest.asJson.noSpaces

  val asset = Asset("id", "projector 1", "projectorUnique1", "projector", true, testTimestamp, testTimestamp)
}
