package com.knoldus.persistence

import java.sql._

case class User(id: String, accessToken: String, empId: String, name: String, email: String,
                password: String, role: String)

class CreateConnection {

  Class.forName("org.postgresql.Driver")

  def insertUserRecord(user: User) = {
    val connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:26257/knoldus?sslmode=disable", "maxroach", "")
    try {
      val query = s"INSERT INTO user VALUES (${user.id},${user.accessToken},${user.empId},${user.name}," +
        s"${user.email},${user.password},${user.role})"
      connection.createStatement().executeUpdate(query)
    } catch {
      case ex: Exception => ex.getMessage
        -9
    } finally {
      connection.close()
    }
  }

  def fetchUserRecord(): Int = {
    val connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:26257/knoldus?sslmode=disable", "maxroach", "")
    try {
      val query = s"SELECT * FROM user"
      connection.createStatement().executeUpdate(query)
    } catch {
      case ex: Exception => ex.getMessage
        -993
    } finally {
      connection.close()
    }
  }
}
