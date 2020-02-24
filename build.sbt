name := "payments_system"

version := "0.1"

scalaVersion := "2.13.1"
val CatsVersion           = "2.0.0"


libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
"org.typelevel"              %% "cats-core"                % CatsVersion,
"org.typelevel"              %% "cats-effect"              % CatsVersion
)