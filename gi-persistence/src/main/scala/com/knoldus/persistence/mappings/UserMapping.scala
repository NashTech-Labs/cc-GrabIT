package com.knoldus.persistence.mappings

import java.sql.Timestamp
import com.knoldus.persistence._
import com.knoldus.utils.models.User

trait UserMapping {
  this: DBComponent =>

  import driver.api._

  class UserMapping(tag: Tag) extends Table[User](tag, "user") {
    def id: Rep[String] = column[String]("id", O.PrimaryKey)
    def accessToken: Rep[String] = column[String]("access_token")
    def employeeId: Rep[String] = column[String]("employee_id")
    def name: Rep[String] = column[String]("name")
    def email: Rep[String] = column[String]("email")
    def password: Rep[String] = column[String]("password")
    def role: Rep[String] = column[String]("role")
    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")
    def lastModifiedAt: Rep[Timestamp] = column[Timestamp]("last_modified_at")


    def * : ProvenShape[User] = (
      id,
      accessToken,
      employeeId,
      name,
      email,
      password,
      role,
      createdAt,
      lastModifiedAt
      ) <>(User.tupled, User.unapply)
  }

  val userInfo: TableQuery[UserMapping] = TableQuery[UserMapping]
}
