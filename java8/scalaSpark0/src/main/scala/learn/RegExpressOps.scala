package learn

/**
  * <pre>
  *
  * 【标题】: 正则
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 11:33
  * </pre>
  */
object RegExpressOps {

  def main(args: Array[String]): Unit = {
    val regex = """([0-9]+) (a-z)""".r
    val numPattern = """\s+[0-9]+""".r
    val numberPattern = """\s+[0-9]+\s""".r
    for (matchString <- numberPattern.findAllIn("99345 Scala, 22298 Spark")){
      println(matchString)
    }
    println(numberPattern.findFirstIn("99ss java, 222 hadoop"))

    val numitemPattern = """([0-9]+) ([a-z]+)""".r
    val numitemPattern(num,item) = "99 hadoop"
    println(num)
    println(item)
    println(numberPattern)
    /* 模式匹配 */
    val line = "93459 spark"
    line match {
      case numitemPattern(num,blog) => println(num + "\t" + blog)
      case _ => println("ohh...")
    }

  }

}
