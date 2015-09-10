name := "Spark_multiple_job_examples";

organization := "xulu";
 
version := "SNAPSHOT-0.1"; 
 
scalaVersion := "2.10.4";


libraryDependencies += "org.apache.spark" %% "spark-assembly" % "1.0.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.4.1"

libraryDependencies += "org.apache.spark" %% "spark-examples" % "1.0.0"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.0.0"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"






