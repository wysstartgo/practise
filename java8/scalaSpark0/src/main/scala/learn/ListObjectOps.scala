package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 17:30
  * </pre>
  */
object ListObjectOps {

  def main(args: Array[String]): Unit = {
    //调用toList
    println(List.apply(1,2,3))
    println(List.range(1,5))
    println(List.range(9,1,-3))

    val zipped = "abcde".toList zip List(1,2,3,4,5)
    println(zipped)
    //zip反操作
    println(zipped.unzip)
    //几个list组成一个
    println(List(List('a','b'),List('c','b'),List('d','e')).flatten)
    println(List.concat(List('b'),List('b'),List('c')))//同flatten
  }

}
