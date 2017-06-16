package com.knoldus.persistence

import com.knoldus.persistence.mappings.UserMapping
import com.knoldus.utils.models.User

import scala.concurrent.Future

trait UserComponent extends UserMapping with DBDriver with GIDBComponent {
  this: DriverComponent =>

  import driver.api._

  /**
    *
    * @param user
    * @return
    */
  def insert(user: User): Future[Int] = {
    db.run(userInfo += user)
  }
}
