package learn

import scala.io.Source

/**
  * <pre>
  *
  * 【标题】: 函数操作
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 11:10
  * </pre>
  */
object FunctionOps {

  def main(args: Array[String]): Unit = {
    val width = 10
    val arr = Array("0","E:\\admin.txt")
    for (arg <- arr.drop(1)) {
      processData(arg,width)
      var increase = (x: Int) => x + 1
      increase(10)
      increase = (x: Int) => x + 9999

      val someNumbers = List(-11,-10,-5,0,5,10)
      someNumbers.foreach((x: Int) => println(x))
      someNumbers.filter((x: Int) => x > 0)
      someNumbers.filter((x) => x > 6)
      val ss = someNumbers.filter(x => x > x)
      println(ss)

    }
  }

  /* 强内聚若耦合 */
  def processData(fileName: String, width: Int): Unit = {
    def processLine(line: String): Unit = {
      if(line.length > width){
        println(fileName + ":" + line)
      }
    }

    val source = Source.fromFile(fileName)
    for(line <- source.getLines()){
      processLine(line)
    }
  }

}
