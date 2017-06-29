package com.knoldus.asset.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.AuthorizationFailedRejection
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.asset.model.AssetRequest
import com.knoldus.asset.service.AssetService
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{AsyncFunSuite, Matchers}
import com.knoldus.asset.TestData._
import com.knoldus.utils.json.JsonHelper
import com.knoldus.utils.models.Asset
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.concurrent.Future

class AssetAPITest extends AsyncFunSuite with ScalatestRouteTest with Matchers with MockitoSugar with JsonHelper {

  val mockAssetService = mock[AssetService]
  val assetAPI = new AssetAPI(mockAssetService)

  import assetAPI._

  test("add asset Api route to insert asset successfully") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockAssetService.insert(assetRequest)).thenReturn(Future.successful(1))
    Post(s"/asset/add?accessToken=$accessToken", assetRequestJson) ~> addAsset ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "Asset has been Successfully Added"
    }
  }

  test("add asset Api route to insert asset when user is not admin") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(false))
    Post(s"/asset/add?accessToken=$accessToken") ~> addAsset ~> check {
      rejection shouldBe AuthorizationFailedRejection
    }
  }

  test("add asset Api route to insert asset when body json could not be decoded") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    Post(s"/asset/add?accessToken=$accessToken", """invalidJson""") ~> addAsset ~> check {
      status shouldBe StatusCodes.BadRequest
      responseAs[String] should include regex "Body params are missing or incorrect"
    }
  }

  test("add asset Api route to insert asset when asset insert service fails") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockAssetService.insert(assetRequest)).thenReturn(Future.failed(new RuntimeException))
    Post(s"/asset/add?accessToken=$accessToken", assetRequestJson) ~> addAsset ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

  test("get All users Api route successfully") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockAssetService.getAllAssets).thenReturn(Future.successful(List(asset)))
    Get(s"/asset/get/all?accessToken=$accessToken") ~> getAllAssets ~> check {
      status shouldBe StatusCodes.OK
      decode[List[Asset]](responseAs[String]).right.get shouldBe List(asset)
    }
  }

  test("get All users Api route when user is not admin") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(false))
    Get(s"/asset/get/all?accessToken=$accessToken") ~> getAllAssets ~> check {
      rejection shouldBe AuthorizationFailedRejection
    }
  }

  test("get All users Api route when asset service fails") {
    when(mockAssetService.isAdmin(accessToken)).thenReturn(Future.successful(true))
    when(mockAssetService.getAllAssets).thenReturn(Future.failed(new RuntimeException))
    Get(s"/asset/get/all?accessToken=$accessToken") ~> getAllAssets ~> check {
      status shouldBe StatusCodes.InternalServerError
      responseAs[String] should include regex "Internal Server Error"
    }
  }

}
