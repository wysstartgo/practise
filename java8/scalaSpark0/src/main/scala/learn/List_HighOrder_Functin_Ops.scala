package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 15:42
  * </pre>
  */
object List_HighOrder_Functin_Ops {

  def main(args: Array[String]): Unit = {
    println(List(1,2,3,4,6,5) map(_+1))

    val data = List("Scala","Hadoop","Spark")
    println(data map(_.length))
    println(data map(_.toList.reverse.mkString))
    println(data.map(_.toList))

    println(data.flatMap(_.toList))
    println(List.range(1,10) flatMap(i => List.range(1,i) map (j => (i,j))))

    var sum = 0
    List(1,2,3,4,6,5) foreach(sum +=_)
    println("Sum:" + sum)

    println(List(1,3,4,5,6,7,8,9,0) filter(_ % 2 == 0))

    println(data filter(_.length == 5))
    //偶数放在一个list中其他的放在另一个历史st中
    println(List(1,2,3,4,5) partition(_%2 == 0))
    //找到第一个符合条件的
    println(List(1,2,3,4,5) find(_%2 == 0))
    println(List.range(1,5) find(_ <= 0))
    //提取满足条件的
    println(List.range(1,5) takeWhile(_<4))
    //删除满足条件的
    println(List.range(1,5) dropWhile(_<4))
    //和partition类似
    println(List.range(1,5) span (_ < 4))

    def hastoTallyZeroRow(m:List[List[Int]]) = m exists(row => row forall(_ == 0))
    val m = List(List(1,0,0),List(0,1,0),List(0,0,0))
    println(hastoTallyZeroRow(m))

  }

}
