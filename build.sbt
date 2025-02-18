inThisBuild(
  Seq(
    scalaVersion := "2.12.17",
    organization := "com.github.ryota0624",
    licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  )
)

lazy val jibCommon = (project in file("jib-common")).settings(
  name := "jib-common",
  crossScalaVersions := List(scalaVersion.value, "2.11.12", "2.13.10", "3.3.0"),
  libraryDependencies ++= List(
    "com.google.cloud.tools" % "jib-core" % "0.27.2",
    "org.scala-lang.modules" %% "scala-collection-compat" % "2.13.0"
  )
)

lazy val sbtJib = (project in file("sbt-jib"))
  .settings(
    name := "sbt-jib"
  )
  .dependsOn(jibCommon)
  .enablePlugins(SbtPlugin)

lazy val root = (project in file("."))
  .settings(
    publish / skip := true,
    crossScalaVersions := Nil
  )
  .aggregate(jibCommon, sbtJib)
