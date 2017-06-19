import sbt._


object Dependencies {

  val resolutionRepos = Seq()

  val akkaVersion = "10.0.7"
  val circeVersion = "0.8.0"

  lazy val akkaHttp           = "com.typesafe.akka"       %%  "akka-http"             % akkaVersion
  lazy val akkaHttpTestKit    = "com.typesafe.akka"       %%  "akka-http-testkit"     % akkaVersion
  lazy val akkaHttpSprayJson  = "com.typesafe.akka"       %%  "akka-http-spray-json"  % akkaVersion
  lazy val circeCore          = "io.circe"                %   "circe-core_2.11"       % circeVersion
  lazy val circeParser        = "io.circe"                %%  "circe-parser"          % circeVersion
  lazy val circeGeneric       = "io.circe"                %%  "circe-generic"         % circeVersion
  lazy val hikariCP           = "com.typesafe.slick"      %%  "slick-hikaricp"        % "3.1.1"
  lazy val javaxEmail         = "javax.mail"              %   "mail"                  % "1.4.7"
  lazy val mockito            = "org.mockito"             %   "mockito-all"           % "1.10.19"
  lazy val postgresql         = "org.postgresql"          %   "postgresql"            % "9.4-1201-jdbc41"
  lazy val pureConfig         = "com.github.pureconfig"   %%  "pureconfig"            % "0.7.2"
  lazy val scalaGuice         = "net.codingwell"          %%  "scala-guice"           % "4.1.0"
  lazy val scalaTest          = "org.scalatest"           %%  "scalatest"             % "2.2.5"
  lazy val slick              = "com.typesafe.slick"      %%  "slick"                 % "3.1.1"
}
