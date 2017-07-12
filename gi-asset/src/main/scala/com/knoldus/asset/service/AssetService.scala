package com.knoldus.asset.service

import com.google.inject.Inject
import com.knoldus.asset.model.AssetRequest
import com.knoldus.persistence.asset.mappings.AssetComponent
import com.knoldus.persistence.user.UserComponent
import com.knoldus.utils.CommonUtility._
import com.knoldus.utils.Constants.Admin
import com.knoldus.utils.models.Asset

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AssetService @Inject()(assetComponent: AssetComponent, userComponent: UserComponent) {

  /**
    * Insert asset record
    * @param assetRequest
    * @return Future[Int]
    */
  def insert(assetRequest: AssetRequest): Future[Int] = {
    val cuurentTime = getCurrentTimestamp
    val assetId = getUUID
    val asset = Asset(assetId, assetRequest.name, assetRequest.uniqueName, assetRequest.assetType, true, cuurentTime, cuurentTime)
    assetComponent.insert(asset)
  }

  /**
    * Fetches list of all assets
    * @return
    */
  def getAllAssets: Future[List[Asset]] = assetComponent.getAllAsset

  /**
    * Fetches list of asset types available
    *
    * @return
    */
  def getAssetTypes: Future[List[String]] = assetComponent.getAllAsset.map(a => a.map(_.assetType).distinct)

  /**
    * Checks if a user is admin
    * @param accessToken
    * @return Future[List[Asset]]
    */
  def isAdmin(accessToken: String): Future[Boolean] = {
    userComponent.getUserByAccessToken(accessToken).map { user =>
      user.fold(false)(user => user.role == Admin)
    }
  }
}
