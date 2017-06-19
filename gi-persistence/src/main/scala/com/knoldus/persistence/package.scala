package com.knoldus

package object persistence {

  type PostgresDbComponent = db.PostgresDbComponent
  type DBComponent = db.DBComponent
  type ProvenShape[A] = slick.lifted.ProvenShape[A]
}
