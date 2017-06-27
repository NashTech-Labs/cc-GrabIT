package com.knoldus.utils.models

import java.sql.Timestamp

case class Asset(
                  id: String,
                  name: String,
                  uniqueName: String,
                  assetType: String,
                  isAvailable: Boolean,
                  createdAt: Timestamp,
                  lastModifiedAt: Timestamp
                )
