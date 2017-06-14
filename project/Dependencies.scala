import sbt._


object Dependencies {

  val resolutionRepos = Seq()

  val akkaVersion = "10.0.7"
  val circeVersion = "0.8.0"

  lazy val akkaHttp           = "com.typesafe.akka"   %%  "akka-http"             % akkaVersion
  lazy val akkaHttpTestKit    = "com.typesafe.akka"   %%  "akka-http-testkit"     % akkaVersion
  lazy val akkaHttpSprayJson  = "com.typesafe.akka"   %%  "akka-http-spray-json"  % akkaVersion
  lazy val mockito            = "org.mockito"         %   "mockito-all"           % "1.10.19"
  lazy val scalaTest          = "org.scalatest"       %%  "scalatest"             % "2.2.5"

  lazy val circeCore          = "io.circe"            %   "circe-core_2.11"       % circeVersion
  lazy val circeParser        = "io.circe"            %%  "circe-parser"          % circeVersion
  lazy val circeGeneric       = "io.circe"            %%  "circe-generic"         % circeVersion

}
