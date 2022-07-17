ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.5"

enablePlugins(ScalaJSPlugin)

lazy val root = (project in file(".")).settings(
  name := "laminar-squares-visualization",
  libraryDependencies ++= Seq(
    "com.raquo" %%% "laminar" % "0.14.2"
  ),
  scalaJSUseMainModuleInitializer := true
)

