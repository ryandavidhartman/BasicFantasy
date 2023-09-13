name := """basic-fantasy-rpg"""
organization := "org.bf"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  // Enable reactive mongo for Play 2.8
  "org.reactivemongo" %% "play2-reactivemongo" % "1.1.0-play28-RC9",
  // Provide JSON serialization for reactive mongo
  "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.1.0-play28-RC9",
  "ch.qos.logback" % "logback-classic" % "1.4.7",

)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.bf.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.bf.binders._"
