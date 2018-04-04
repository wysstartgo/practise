import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: word count
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-02 11:22
  * </pre>
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    val inputFile = "E:\\README.md"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey((a,b) => a + b)
    wordCount.foreach(println)


  }


}


