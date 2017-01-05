import sbt._



object StubServiceBuild extends Build with MicroService {
  import scala.util.Properties.envOrElse

  val appName = "investment-tax-relief-subscription-dynamic-stub"

  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  import play.core.PlayVersion
  import play.sbt.PlayImport._


  private val microserviceBootstrapVersion = "5.8.0"
  private val playHealthVersion = "2.0.0"
  private val playConfigVersion = "3.0.0"
  private val hmrcTestVersion = "2.2.0"
  private val logbackJsonLoggerVersion = "3.1.0"
  private val scalaTestVersion = "2.2.6"
  private val pegdownVersion = "1.6.0"
  private val domainVersion = "4.0.0"
  private val playUrlBindersVersion = "2.0.0"
  private val mockitoAll = "1.9.5"
  private val scalaTestPlus = "1.5.1"


  
  val compile = Seq(
    ws,
    "uk.gov.hmrc" %% "microservice-bootstrap" % microserviceBootstrapVersion,
    "uk.gov.hmrc" %% "play-health" % playHealthVersion,
    "uk.gov.hmrc" %% "play-config" % playConfigVersion,
    "uk.gov.hmrc" %% "logback-json-logger" % logbackJsonLoggerVersion,
    "uk.gov.hmrc" %% "play-reactivemongo" % "5.1.0",
    "uk.gov.hmrc" %% "play-url-binders" % playUrlBindersVersion,
    "uk.gov.hmrc" %% "domain" % domainVersion,
    "org.mockito" % "mockito-all" % mockitoAll,
    "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlus
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % hmrcTestVersion % scope,
        "org.scalatest" %% "scalatest" % scalaTestVersion % scope,
        "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % scope,
        "org.pegdown" % "pegdown" % pegdownVersion % scope,
        "org.mockito" % "mockito-all" % mockitoAll,
        "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlus
      )
    }.test
  }

  def apply() = compile ++ Test()
}


