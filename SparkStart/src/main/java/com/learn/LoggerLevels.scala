//package com.learn
//
//import org.apache.log4j.{Level, Logger}
//import org.apache.spark.Logging
//
///**
//  * <pre>
//  *
//  * @title: LoggerLevels
//  * @description:
//  * @company: 润投科技
//  * @author: wuys
//  * @datetime: 2019-04-30 16:07
//  *            </pre>
//  */
//object LoggerLevels extends Logging{
//
//  def setStreamingLogLevels(): Unit ={
//    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
//    if (!log4jInitialized){
//      logInfo("Setting log level to [WARN] for streaming example." + " To override add a custom log4j.properties to the classpath.")
//      Logger.getRootLogger.setLevel(Level.WARN)
//    }
//  }
//
//}
