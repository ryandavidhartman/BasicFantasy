enablePlugins(ScalaJSPlugin)

name := "OnceWas RPG Character Generator"
Compile / scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xfatal-warnings")
scalaVersion := "2.13.13" // Scala 2.12+ recommended for Scala.js 1.x

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "2.0.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.4" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

Compile / jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
