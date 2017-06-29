import BuildSettings._
import Dependencies._


def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

// -------------------------------------------------------------------------------------------------------------------
// Root Project
// -------------------------------------------------------------------------------------------------------------------

lazy val root = Project("grabit", file("."))
  .aggregate(giUtils, giUser, giPersistence, giAsset, giBooking, giPortal, giNotify)
  .settings(basicSettings: _*)

// -------------------------------------------------------------------------------------------------------------------
// UTILS
// -------------------------------------------------------------------------------------------------------------------

lazy val giUtils = Project("gi-utils", file("gi-utils"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(circeGeneric, circeParser, circeCore) ++ test())

// -------------------------------------------------------------------------------------------------------------------
// USER
// -------------------------------------------------------------------------------------------------------------------

lazy val giUser = Project("gi-user", file("gi-user"))
  .dependsOn(giUtils, giPersistence, giNotify)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(akkaHttp, akkaHttpCors, scalaGuice, jbCrypt) ++ test(akkaHttpTestKit, mockito, scalaTest))

// -------------------------------------------------------------------------------------------------------------------
// PERSISTENCE
// -------------------------------------------------------------------------------------------------------------------

lazy val giPersistence = Project("gi-persistence", file("gi-persistence"))
  .dependsOn(giUtils)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(postgresql, slick, hikariCP, scalaGuice) ++ test(h2DB, scalaTest))

// -------------------------------------------------------------------------------------------------------------------
// ASSET
// -------------------------------------------------------------------------------------------------------------------

lazy val giAsset = Project("gi-asset", file("gi-asset"))
  .dependsOn(giUtils, giPersistence)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(akkaHttp, akkaHttpCors, scalaGuice) ++ test(akkaHttpTestKit, mockito, scalaTest))

// -------------------------------------------------------------------------------------------------------------------
// BOOKING
// -------------------------------------------------------------------------------------------------------------------

lazy val giBooking = Project("gi-booking", file("gi-booking"))
  .dependsOn(giUtils, giPersistence)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(akkaHttp, akkaHttpCors, scalaGuice, jbCrypt) ++ test(akkaHttpTestKit, mockito, scalaTest))

// -------------------------------------------------------------------------------------------------------------------
// NOTIFY
// -------------------------------------------------------------------------------------------------------------------

lazy val giNotify = Project("gi-notify", file("gi-notify"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(javaxEmail, pureConfig) ++ test(scalaTest))

// -------------------------------------------------------------------------------------------------------------------
// PORTAL
// -------------------------------------------------------------------------------------------------------------------

lazy val giPortal = Project("gi-portal", file("gi-portal"))
