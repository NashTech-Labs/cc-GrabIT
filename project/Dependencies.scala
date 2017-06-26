import sbt._


object Dependencies {

  val resolutionRepos = Seq()

  val akkaVersion = "10.0.7"
  val circeVersion = "0.8.0"
  val hikariSlickVersion = "3.2.0"
  val mockitoVersion = "1.10.19"
  val postgresqlVersion = "9.4.1212"
  val scalaGuiceVersion = "4.1.0"
  val scalaTestVersion = "3.0.1"
  val javaxMailVersion = "1.4.7"
  val pureConfigVersion = "0.7.2"
  val jbCryptVersion = "0.4"
  val h2DBVersion = "1.4.193"

  lazy val akkaHttp           = "com.typesafe.akka"       %%  "akka-http"             % akkaVersion
  lazy val akkaHttpCors       = "ch.megard"               %%  "akka-http-cors"        % "0.2.1"
  lazy val akkaHttpTestKit    = "com.typesafe.akka"       %%  "akka-http-testkit"     % akkaVersion
  lazy val circeCore          = "io.circe"                %   "circe-core_2.11"       % circeVersion
  lazy val circeParser        = "io.circe"                %%  "circe-parser"          % circeVersion
  lazy val circeGeneric       = "io.circe"                %%  "circe-generic"         % circeVersion
  lazy val hikariCP           = "com.typesafe.slick"      %%  "slick-hikaricp"        % hikariSlickVersion
  lazy val h2DB               = "com.h2database"          %   "h2"                    % h2DBVersion
  lazy val javaxEmail         = "javax.mail"              %   "mail"                  % javaxMailVersion
  lazy val jbCrypt            = "org.mindrot"             %  "jbcrypt"                % jbCryptVersion
  lazy val mockito            = "org.mockito"             %   "mockito-all"           % mockitoVersion
  lazy val postgresql         = "org.postgresql"          %   "postgresql"            % postgresqlVersion
  lazy val pureConfig         = "com.github.pureconfig"   %%  "pureconfig"            % pureConfigVersion
  lazy val scalaGuice         = "net.codingwell"          %%  "scala-guice"           % scalaGuiceVersion
  lazy val scalaTest          = "org.scalatest"           %%  "scalatest"             % scalaTestVersion
  lazy val slick              = "com.typesafe.slick"      %%  "slick"                 % hikariSlickVersion
}
