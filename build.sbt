name := "HelloWorld"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.jsoup" % "jsoup" % "1.7.3" % "test",
  "org.scalatest" %% "scalatest" % "2.1.5" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0"
)     

play.Project.playJavaSettings
