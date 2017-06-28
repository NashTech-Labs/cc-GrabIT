package com.knoldus.persistence.asset

import java.sql.Timestamp

import com.knoldus.persistence.asset.mappings.AssetComponent
import com.knoldus.persistence.db.H2DBComponent
import com.knoldus.utils.models.Asset
import org.h2.jdbc.JdbcSQLException
import org.scalatest.AsyncFunSuite

class AssetComponentTest extends AsyncFunSuite with AssetComponent with H2DBComponent {

  test("Insert asset functionality") {
    val timestamp = new Timestamp(123)
    val asset = Asset("id-3", "test asset", "asset-unique-3", "projector", false, timestamp, timestamp)
    val result = insert(asset)
    result.map { count => assert(count === 1)}
  }

  test("Insert asset functionality when primary key already exist") {
    val timestamp = new Timestamp(123)
    val asset = Asset("id-1", "test asset", "asset-unique-2", "projector", false, timestamp, timestamp)
    recoverToSucceededIf[JdbcSQLException](insert(asset))
  }

  test("Insert asset functionality with duplicate asset unique name") {
    val timestamp = new Timestamp(123)
    val asset = Asset("id-2", "test asset", "asset-unique-1", "projector", false, timestamp, timestamp)
    recoverToSucceededIf[JdbcSQLException](insert(asset))
  }

  test("Fetch all asset"){
    val result = getAllAsset
    result.map { assets =>
      assert(assets.length === 1)
    }
  }

}
