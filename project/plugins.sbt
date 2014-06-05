// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//resolvers += Resolver.url("sbt-plugin-releases",
//  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(
//    Resolver.ivyStylePatterns)

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "0.99.2.2")

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2")