import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-02 15:26
  * </pre>
  */
object TestRDD {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TestRDD").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //    val data = Array(1,2,3,4,5,6)
    //    val distData = sc.parallelize(data)
    //    distData.foreach(println)
    val distFile = sc.textFile("F:\\workspace\\scalaSpark0\\src\\main\\scala\\rdd.txt")
    //distFile.foreach(println)
    val user_rdd = distFile.map(f => {
      val array = f.split(",")
      val tup = (array(0), array(1), array(2).toDouble)
      tup
    })
    //user_rdd.foreach(println)

    val user_rdd2 = user_rdd.map(f => (f._1, f._2)).sortByKey()

    user_rdd2.cache
    //1 (用户：物品)笛卡尔积 (用户：物品) =>物品:物品组合
    //user_rdd2.foreach(println)

    val user_rdd3 = user_rdd2 join user_rdd2
    user_rdd3.foreach(println)

    val user_rdd4 = user_rdd3.map(data => (data._2, 1))
    println("=================================================")
    user_rdd4.foreach(println)
    //物品:物品:频次
    val user_rdd5 = user_rdd4.reduceByKey((x, y) => x + y)
    user_rdd5.foreach(println)

    //对角矩阵
    val user_rdd6 = user_rdd5.filter(f => f._1._1 == f._1._2)

    user_rdd6.foreach(println)

    //非对角矩阵
    val user_rdd7 = user_rdd5.filter(f => f._1._1 != f._1._2)

    //计算同现相似度(物品1,物品2,同现频次)
    val user_rdd8 = user_rdd7.map(f => (f._1._1, (f._1._1, f._1._2, f._2))).join(user_rdd6.map(f => (f._1._1, f._2)))
    println("====================================8========================")
    user_rdd8.foreach(println)

    val user_rdd9 = user_rdd8.map(f => (f._2._1._2, (f._2._1._1, f._2._1._2, f._2._1._3, f._2._2)))
    println("================================9=================================")
    user_rdd9.foreach(println)
    val user_rdd10 = user_rdd9.join(user_rdd6.map(f => (f._1._1, f._2)))
    println("================================10=================================")
    user_rdd10.foreach(println)

    val user_rdd11 = user_rdd10.map(f => (f._2._1._1, f._2._1._2, f._2._1._3, f._2._1._4, f._2._2))
    println("================================11=================================")
    user_rdd11.foreach(println)
    val user_rdd12 = user_rdd11.map(f => (f._1, f._2, (f._3 / math.sqrt(f._4 * f._5))))
    println("================================12=================================")
    user_rdd12.foreach(println)
  }

}
