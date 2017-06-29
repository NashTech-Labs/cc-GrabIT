package com.knoldus.asset.model

case class AssetRequest(name: String, uniqueName: String, assetType: String) {
    require(name.trim.length > 0, "Name should not be empty")
    require(uniqueName.trim.length > 0, "Unique Name should not be empty")
    require(assetType.trim.length > 0, "Asset type should not be empty")
}
