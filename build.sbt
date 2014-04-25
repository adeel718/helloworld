name := "HelloWorld"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.jsoup" % "jsoup" % "1.7.3" % "test"
)     

play.Project.playJavaSettings
