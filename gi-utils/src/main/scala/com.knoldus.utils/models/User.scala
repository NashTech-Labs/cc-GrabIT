package com.knoldus.utils.models

import java.sql.Timestamp

case class User(id: String, accessToken: String, employeeId: String, name: String, email: String,
                password: String, role: String, createdAt: Timestamp, lastModifiedAt: Timestamp)

