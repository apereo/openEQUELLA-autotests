val circeVersion = "0.9.3"
val http4sVersion = "0.18.14"
val catsVersion = "1.1.0"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies ++= Seq(
  "javax.jws" % "javax.jws-api" % "1.1",
  "org.apache.commons" % "commons-lang3" % "3.7",
  "org.seleniumhq.selenium" % "selenium-java" % "3.141.59",
  "org.easytesting" % "fest-util" % "1.2.5",
  "org.easytesting" % "fest-swing" % "1.2.1",
  "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13",
  "xalan" % "xalan" % "2.7.2",
  "org.dspace.oclc" % "oclc-srw" % "1.0.20080328",
  "org.apache.cxf" % "cxf-rt-frontend-simple" % "3.1.12",
  "org.apache.cxf" % "cxf-rt-databinding-aegis" % "3.1.12",
  "org.apache.cxf" % "cxf-rt-transports-http" % "3.1.12",
  "org.apache.httpcomponents" % "httpclient" % "4.5.5",
//  "org.apache.cxf" % "cxf-bundle" % "2.7.6",
  "axis" % "axis" % "1.4",
  "com.jcraft" % "jsch" % "0.1.54",
//  "jpf" % "jpf-tools" % "1.0.5",
  "org.jacoco" % "org.jacoco.report" % "0.7.9",
  "org.dspace" % "oclc-harvester2" % "0.1.12",
  "org.jvnet.hudson" % "xstream" % "1.3.1-hudson-8",
  "com.typesafe" % "config" % "1.3.1",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "org.scalacheck" %% "scalacheck" % "1.13.5" % Test,
  "org.http4s" %% "http4s-async-http-client" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.typelevel" %% "cats-free" % catsVersion
)

unmanagedBase in Compile := baseDirectory.value / "lib/adminjars"

testOptions in Test := Seq(
  Tests.Argument(TestFrameworks.ScalaCheck, "-s", "1")
)

parallelExecution in Test := buildConfig.value.getBoolean("tests.parallel")
