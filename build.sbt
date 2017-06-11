name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0-M3" % Test,
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.typesafe.slick" % "slick_2.12" % "3.2.0",
  "com.typesafe.slick" % "slick-hikaricp_2.12" % "3.2.0",
  "com.typesafe.play" % "play_2.12" % "2.6.0-M5",
  "com.typesafe.play" %% "play-slick" % "3.0.0-M3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0-M3"
)