name := """basic-fantasy-rpg"""
organization := "org.bf"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.16"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test,
  // Enable reactive mongo for Play 2.8
  "org.reactivemongo" %% "play2-reactivemongo" % "1.1.0-play30.RC14",
  // Provide JSON serialization for reactive mongo
  "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.1.0-play210.RC14",
  "ch.qos.logback" % "logback-classic" % "1.5.16",

)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.bf.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.bf.binders._"
