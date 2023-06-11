enablePlugins(ScalaJSPlugin)

name := "Basic Fantasy Character Generator"

scalaVersion := "2.13.1" // or any other Scala version >= 2.11.12

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "2.0.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.4" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()