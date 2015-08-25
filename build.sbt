name := """play-java-first-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "commons-daemon" % "commons-daemon" % "1.0.15",
  "junit" % "junit" % "4.8.1" % "test",
  "com.google.code.gson" % "gson" % "2.2.4",
  "org.squeryl" % "squeryl_2.10" % "0.9.5-6",
  "com.twitter" % "util-zk_2.10" % "6.11.0",
  "com.twitter" % "finagle-core_2.10" % "6.15.0",
  "org.quartz-scheduler" % "quartz" % "2.2.1",
  "org.apache.kafka" % "kafka_2.11" % "0.8.2.1"
    exclude("javax.jms", "jms")
    exclude("com.sun.jdmk", "jmxtools")
    exclude("com.sun.jmx", "jmxri")
)



// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
