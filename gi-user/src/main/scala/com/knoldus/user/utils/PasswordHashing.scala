package com.knoldus.user.utils

import com.google.inject.ImplementedBy
import org.mindrot.jbcrypt.BCrypt

@ImplementedBy(PasswordHashing)
trait PasswordHashingComponent {

  /**
    * Generate hashed password
    *
    * @param password
    * @return hash password
    */
  def generateHashedPassword(password: String): String = {
    val salt = BCrypt.gensalt()
    BCrypt.hashpw(password, salt)
  }

  /**
    * Match hash password
    *
    * @param password     string
    * @param passwordHash hash (encrypted)
    * @return match status
    */
  def passwordMatches(password: String, passwordHash: String): Boolean = {
    BCrypt.checkpw(password, passwordHash)
  }

}

object PasswordHashing extends PasswordHashingComponent
