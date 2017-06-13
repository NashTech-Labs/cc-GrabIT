import sbt._
import Keys._


object BuildSettings {
  val VERSION = "SNAPSHOT"

  lazy val basicSettings = Seq(
    version := VERSION,
    organization := "com.knoldus",
    organizationHomepage := Some(new URL("http://knoldus.com/")),
    scalaVersion := "2.11.8",
    fork := true,
    ivyScala := ivyScala.value map(_.copy(overrideScalaVersion = true)),
    scalacOptions := Seq("-target:jvm-1.8", "-encoding", "UTF-8", "-deprecation", "-unchecked"),
    parallelExecution in Test := false
  )

}
