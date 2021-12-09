scalaVersion := "2.13.3" // Also supports 2.11.x and 2.12.x

val http4sVersion = "1.0.0-M30"
val specsVersion = "4.10.0"
val CirceVersion = "0.14.0-M1"
val CirceGenericExtrasVersion = "0.14.1"

lazy val root = (project in file("."))
  .settings(
    organization := "com.onekhan",
    name := "vault4s",
    version := "0.1.0",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-core" % http4sVersion,
      "org.http4s" %% "http4s-client" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "io.circe" %% "circe-generic" % CirceVersion,
      "io.circe" %% "circe-generic-extras" % CirceGenericExtrasVersion,
      "org.specs2" %% "specs2-core" % specsVersion % "test"
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
    mimaPreviousArtifacts := Set.empty
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)
