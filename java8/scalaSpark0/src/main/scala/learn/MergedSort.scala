package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 17:35
  * </pre>
  */
object MergedSort {

  def main(args: Array[String]): Unit = {
    def mergedSort[T](less: (T, T) => Boolean)(input: List[T]): List[T] = {

      def merge(xList: List[T], yList: List[T]): List[T] = (xList, yList) match {
        case (Nil, _) => yList
        case (_, Nil) => xList
        case (x :: xtail, y :: ytail) =>
          if (less(x, y)) x :: merge(xtail, yList)
          else y :: merge(xList, ytail)
      }

      val n = input.length / 2
      if (n == 0) {
        input
      } else {
        val (x, y) = input splitAt n
        merge(mergedSort(less)(x), mergedSort(less)(y))
      }
    }

    println(mergedSort((x: Int, y: Int) => x < y)(List(3, 7, 9, 5)))
    val reversed_mergedSort = mergedSort((x: Int, y: Int) => x > y) _
    println(reversed_mergedSort(List(3, 7, 9, 5)))
  }

}
