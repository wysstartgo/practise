package learn.pattern_match

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 19:36
  * </pre>
  */
object Hello_Pattern_Match {

  def main(args: Array[String]): Unit = {
    val data = 2
    data match {
      case 1 => println("First")
      case 2 => println("Second")
      case _ => println("Not Known Number")
    }

    val result = data match {
      case i if i==1 => "The First"
      case number if number == 2 => "The Second"
      case _ => "Not Known Number"
    }

    println(result)

    "Spark !".foreach{
      c => println(
        c match{
          case ' ' => "space"
          case ch => "Char: " + ch
        }
      )
    }

  }

}
