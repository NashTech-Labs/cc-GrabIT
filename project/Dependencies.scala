import sbt._


object Dependencies {

  val resolutionRepos = Seq()

  val akkaVersion = "10.0.7"
  val circeVersion = "0.8.0"
  val hikariSlickVersion = "3.1.1"
  val mockitoVersion = "1.10.19"
  val postgresqlVersion = "9.4-1201-jdbc41"
  val scalaGuiceVersion = "4.1.0"
  val scalaTestVersion = "2.2.5"
  val javaxMailVersion = "1.4.7"
  val pureConfigVersion = "0.7.2"

  lazy val akkaHttp           = "com.typesafe.akka"       %%  "akka-http"             % akkaVersion
  lazy val akkaHttpTestKit    = "com.typesafe.akka"       %%  "akka-http-testkit"     % akkaVersion
  lazy val akkaHttpSprayJson  = "com.typesafe.akka"       %%  "akka-http-spray-json"  % akkaVersion
  lazy val circeCore          = "io.circe"                %   "circe-core_2.11"       % circeVersion
  lazy val circeParser        = "io.circe"                %%  "circe-parser"          % circeVersion
  lazy val circeGeneric       = "io.circe"                %%  "circe-generic"         % circeVersion
  lazy val hikariCP           = "com.typesafe.slick"      %%  "slick-hikaricp"        % hikariSlickVersion
  lazy val javaxEmail         = "javax.mail"              %   "mail"                  % javaxMailVersion
  lazy val mockito            = "org.mockito"             %   "mockito-all"           % mockitoVersion
  lazy val postgresql         = "org.postgresql"          %   "postgresql"            % postgresqlVersion
  lazy val pureConfig         = "com.github.pureconfig"   %%  "pureconfig"            % pureConfigVersion
  lazy val scalaGuice         = "net.codingwell"          %%  "scala-guice"           % scalaGuiceVersion
  lazy val scalaTest          = "org.scalatest"           %%  "scalatest"             % scalaTestVersion
  lazy val slick              = "com.typesafe.slick"      %%  "slick"                 % hikariSlickVersion
}
