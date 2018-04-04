package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 15:36
  * </pre>
  */
object List_Fold_Sort {

  def main(args: Array[String]): Unit = {
    println((1 to 100).foldLeft(0)(_ + _))

    println((0 /: (1 to 100))(_ + _))

    println((1 to 5).foldRight(100)(_-_))

    println(((1 to 5):\100)(_-_))

    println(List(1,-3,4,3,6) sortWith(_<_))

    println(List(1,-3,4,3,6) sortWith(_>_))
  }

}
