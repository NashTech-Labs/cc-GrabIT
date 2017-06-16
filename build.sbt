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
  .dependsOn(giRegister, giUtils)
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
// REGISTER
// -------------------------------------------------------------------------------------------------------------------

lazy val giRegister = Project("gi-register", file("gi-register"))
  .dependsOn(giUtils)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test(scalaTest, mockito))

// -------------------------------------------------------------------------------------------------------------------
// BOOKING
// -------------------------------------------------------------------------------------------------------------------

lazy val giBooking = Project("gi-booking", file("gi-booking"))
  .dependsOn(giUtils)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test(scalaTest, mockito))

// -------------------------------------------------------------------------------------------------------------------
// NOTIFY
// -------------------------------------------------------------------------------------------------------------------

lazy val giNotify = Project("gi-notify", file("gi-notify"))
  .dependsOn(giUtils)
  .settings(basicSettings: _*)
  .settings(libraryDependencies ++= compile() ++ test(scalaTest, mockito))

// -------------------------------------------------------------------------------------------------------------------
// PORTAL
// -------------------------------------------------------------------------------------------------------------------

lazy val giPortal = Project("gi-portal", file("gi-portal"))
