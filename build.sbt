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
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// USER
// -------------------------------------------------------------------------------------------------------------------

lazy val giUser = Project("gi-user", file("gi-user"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(akkaHttp, akkaHttpSprayJson) ++ test(akkaHttpTestKit, scalaTest, mockito))

// -------------------------------------------------------------------------------------------------------------------
// PERSISTENCE
// -------------------------------------------------------------------------------------------------------------------

lazy val giPersistence = Project("gi-persistence", file("gi-persistence"))
  .dependsOn(giUtils)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(postgresql, slick, hikariCP) ++ test())

// -------------------------------------------------------------------------------------------------------------------
// ASSET
// -------------------------------------------------------------------------------------------------------------------

lazy val giAsset = Project("gi-asset", file("gi-asset"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// BOOKING
// -------------------------------------------------------------------------------------------------------------------

lazy val giBooking = Project("gi-booking", file("gi-booking"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// NOTIFY
// -------------------------------------------------------------------------------------------------------------------

lazy val giNotify = Project("gi-notify", file("gi-notify"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// PORTAL
// -------------------------------------------------------------------------------------------------------------------

lazy val giPortal = Project("gi-portal", file("gi-portal"))
