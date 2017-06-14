import BuildSettings._
import Dependencies._


def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

// -------------------------------------------------------------------------------------------------------------------
// Root Project
// -------------------------------------------------------------------------------------------------------------------

lazy val root = Project("grabit", file("."))
  .aggregate(giUtils, giApi, giPersistence, giRegister, giBooking, giPortal, giNotify)
  .settings(basicSettings: _*)

// -------------------------------------------------------------------------------------------------------------------
// UTIL S
// -------------------------------------------------------------------------------------------------------------------

lazy val giUtils = Project("gi-utils", file("gi-utils"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// API
// -------------------------------------------------------------------------------------------------------------------

lazy val giApi = Project("gi-api", file("gi-api"))
  .dependsOn(giRegister)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile(akkaHttp, akkaHttpSprayJson) ++ test(akkaHttpTestKit, scalaTest, mockito))

// -------------------------------------------------------------------------------------------------------------------
// PERSISTENCE
// -------------------------------------------------------------------------------------------------------------------

lazy val giPersistence = Project("gi-persistence", file("gi-persistence"))
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test())

// -------------------------------------------------------------------------------------------------------------------
// REGISTER
// -------------------------------------------------------------------------------------------------------------------

lazy val giRegister = Project("gi-register", file("gi-register"))
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
