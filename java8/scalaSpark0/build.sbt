name := "scalaSpark0"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1", //version changed as these the only versions supported by 2.12
  "org.scala-js" %% "scalajs-test-interface" % "0.6.14",
  "com.novocode" % "junit-interface" % "0.11",
  "org.scala-lang" % "scala-library" % scalaVersion.value
)

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.3.0"

//libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.2" % "provided"
//
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.2" % "provided"
//
//libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.2"  //注意版本