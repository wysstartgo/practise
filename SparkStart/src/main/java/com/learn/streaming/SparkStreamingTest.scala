import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}

/*
* Spark Streaming 整合 Spark SQL 完成词频统计
* */
object SparkStreamingTest {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("SqlNetworkWordCount").setMaster("local[2]")

    val ssc = new StreamingContext(sparkConf,Seconds(5))

    val lines = ssc.socketTextStream("localhost",6789)
    val words = lines.flatMap(_.split(" ")).map(word => (word,1)).
      reduceByKeyAndWindow((x:Int,y:Int) =>{x+ y},Seconds(6),Seconds(4))

    // Convert RDDs of the words DStream to DataFrame and run SQL query
//    words.foreachRDD { (rdd: RDD[String], time: Time) =>
//      // Get the singleton instance of SparkSession
//      val spark = SparkSessionSingleton.getInstance(rdd.sparkContext.getConf)
//
//      // Convert RDD[String] to RDD[case class] to DataFrame
//      val wordsDataFrame = rdd.map(w => Record(w))
//
//      // Creates a temporary view using the DataFrame
//      wordsDataFrame.createOrReplaceTempView("words")
//
//      // Do word count on table using SQL and print it
//      val wordCountsDataFrame =
//        spark.sql("select word, count(*) as total from words group by word")
//      println(s"========= $time =========")
//      wordCountsDataFrame.show()
//    }


    ssc.start()
    ssc.awaitTermination()

  }


  /** Case class for converting RDD to DataFrame */
  case class Record(word: String)

  /** Lazily instantiated singleton instance of SparkSession */
  object SparkSessionSingleton {

    @transient  private var instance: SparkSession = _

    def getInstance(sparkConf: SparkConf): SparkSession = {
      if (instance == null) {
        instance = SparkSession
          .builder
          .config(sparkConf)
          .getOrCreate()
      }
      instance
    }
  }


}

