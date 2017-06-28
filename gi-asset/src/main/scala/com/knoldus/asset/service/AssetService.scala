package com.knoldus.asset.service

import com.google.inject.Inject
import com.knoldus.asset.model.AssetRequest
import com.knoldus.persistence.asset.mappings.AssetComponent
import com.knoldus.persistence.user.UserComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.models.Asset
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AssetService @Inject()(assetComponent: AssetComponent, userComponent: UserComponent) {

  /**
    * Insert asset record
    * @param assetRequest
    * @return
    */
  def insert(assetRequest: AssetRequest): Future[Int] = {
    val timestamp = getCurrentTimestamp
    val assetId = getUUID
    val asset = Asset(assetId, assetRequest.name, assetRequest.uniqueName, assetRequest.assetType, true, timestamp, timestamp)
    assetComponent.insert(asset)
  }

  /**
    * Checks if a user is admin
    * @param accessToken
    * @return
    */
  def isAdmin(accessToken: String): Future[Boolean] = {
    userComponent.getUserByAccessToken(accessToken).map { user =>
      user.fold(false)(user => user.role == "admin")
    }
  }
}
