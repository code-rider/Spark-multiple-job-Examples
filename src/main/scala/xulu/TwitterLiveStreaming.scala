package xulu

import org.apache.spark._
import org.apache.spark.streaming._
import StreamingContext._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.api.java.JavaStreamingContext
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream

object TwitterLiveStreaming {
  
  def main(args: Array[String]) {
    if (args.length < 1) {
      sys.error("Arguments: <credential_file>")
    }
    
    val TwitterCredentialFilePath = args(0)
    
    val sparkConf = new SparkConf().setAppName("Twitter Streaming sbt")
    
    // Configure Twitter credentials using TwitterCredential.txt
    TutorialHelper.configureTwitterCredentials(TwitterCredentialFilePath)
    val ssc = new JavaStreamingContext(sparkConf,Seconds(15))
    val tweets : JavaReceiverInputDStream[twitter4j.Status] = TwitterUtils.createStream(ssc)   
    
    val statuses = tweets.dstream.map(status => status.getText())
    statuses.print()
    // Your code goes here
    
    ssc.checkpoint("hdfs://HDFS_IP:9000/checkpoint");
    ssc.start()
    ssc.awaitTermination()
  }
}