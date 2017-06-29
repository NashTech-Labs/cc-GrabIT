package com.knoldus.asset.service

import com.knoldus.asset.model.AssetRequest
import com.knoldus.persistence.asset.mappings.AssetComponent
import com.knoldus.persistence.user.UserComponent
import com.knoldus.utils.models.Asset
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{AsyncFunSuite, _}
import com.knoldus.asset.TestData._
import scala.concurrent.Future

class AssetServiceTest extends AsyncFunSuite with Matchers with MockitoSugar {

  val mockAssetComponent = mock[AssetComponent]
  val mockUserComponent = mock[UserComponent]

  val assetService = new AssetService(mockAssetComponent, mockUserComponent)

  test("route for adding asset") {
    val assetRequest = AssetRequest("test asset", "asset-unique-2", "projector")
    when(mockAssetComponent.insert(any[Asset])).thenReturn(Future.successful(1))
    val result = assetService.insert(assetRequest)
    result.map(count => count shouldBe 1)
  }

  test("whether user is admin successfully") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(Some(user)))
    val result = assetService.isAdmin(accessToken)
    result.map(isAdmin => isAdmin shouldBe true)
  }

  test("whether user is admin if role is Employee") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(Some(user.copy(role = "Employee"))))
    val result = assetService.isAdmin(accessToken)
    result.map(isAdmin => isAdmin shouldBe false)
  }

  test("whether user is admin if user is not present") {
    when(mockUserComponent.getUserByAccessToken(accessToken)).thenReturn(Future.successful(None))
    val result = assetService.isAdmin(accessToken)
    result.map(isAdmin => isAdmin shouldBe false)
  }

  test("get all assets functionality") {
    when(mockAssetComponent.getAllAsset).thenReturn(Future.successful(List(asset)))
    val result = assetService.getAllAssets
    result.map(assets => assets shouldBe List(asset))
  }
}
