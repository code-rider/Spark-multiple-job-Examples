package xulu

import org.apache.spark.streaming._
import org.apache.spark.storage.StorageLevel
import scala.io.Source
import scala.collection.mutable.HashMap
import java.io.File
import org.apache.log4j.Logger
import org.apache.log4j.Level
import sys.process.stringSeqToProcess
import java.util.Properties
import java.io._

object TutorialHelper {
  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.apache.spark.streaming.NetworkInputTracker").setLevel(Level.INFO)
    
  /** Configures the Oauth Credentials for accessing Twitter */
  def configureTwitterCredentials(FilePath:String) {
    
    val properties = new Properties()
    properties.load(new FileInputStream(FilePath))
    
    
    val apiKey = properties.getProperty("TWITTER_API_KEY").trim
    val apiSecret = properties.getProperty("TWITTER_API_SECRET").trim
    val accessToken = properties.getProperty("TWITTER_ACCESS_TOKEN").trim
    val accessTokenSecret = properties.getProperty("TWITTER_ACCESS_TOKEN_SECRET").trim
  
    System.setProperty("twitter4j.oauth.consumerKey", apiKey)
    println("\ttwitter4j.oauth.consumerKey set as " + apiKey) 
    
    System.setProperty("twitter4j.oauth.consumerSecret", apiSecret)
    println("\ttwitter4j.oauth.consumerSecret set as " + apiSecret) 
    
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    println("\ttwitter4j.oauth.accessToken set as " + accessToken)
    
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)
    println("\ttwitter4j.oauth.accessTokenSecret " + accessTokenSecret)
    
    println("Oauth Credentials Configured for accessing Twitter")
    
  }
}