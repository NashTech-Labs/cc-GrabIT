package com.knoldus.persistence.asset.mappings

import com.knoldus.persistence.PostgresDbComponent
import com.knoldus.utils.models.Asset

import scala.concurrent.Future

class AssetComponent extends AssetMapping with PostgresDbComponent {

  import driver.api._

  /**
    * Insert asset into database
    *
    * @param asset
    * @return
    */

  def insert(asset: Asset): Future[Int] = {
    db.run(assetInfo += asset)
  }

  /**
    * Fetches All asset from DB
    *
    * @return
    */
  def getAllAsset: Future[List[Asset]] = {
    db.run(assetInfo.to[List].result)
  }

}
