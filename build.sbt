import com.typesafe.sbteclipse.core._
import scala.xml.transform.RewriteRule

name := "HelloWorld"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb" %% "casbah" % "2.7.2",
  "com.novus" %% "salat" % "1.9.8",
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.jsoup" % "jsoup" % "1.7.3" % "test",
  "org.scalatestplus" %% "play" % "1.0.1" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0" % "test"
)

play.Project.playJavaSettings


instrumentSettings

ScoverageKeys.minimumCoverage := 70

ScoverageKeys.failOnMinimumCoverage := false

ScoverageKeys.highlighting := {
  if (scalaBinaryVersion.value == "2.10") false
  else false
}

publishArtifact in Test := false

parallelExecution in Test := false

Keys.fork in (Test) := false

EclipseKeys.projectFlavor := EclipseProjectFlavor.Scala

EclipseKeys.projectTransformerFactories := Seq[EclipseTransformerFactory[RewriteRule]]()

scalacOptions ++= Seq("-feature")