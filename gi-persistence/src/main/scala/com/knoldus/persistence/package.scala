package com.knoldus

package object persistence {

  type GIDBComponent = db.GIDBComponent
  type DBDriver = driver.DBDriver
  type DBComponent = db.DBComponent
  type DriverComponent = driver.DriverComponent
  type ProvenShape[A] = slick.lifted.ProvenShape[A]
}
