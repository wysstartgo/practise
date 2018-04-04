package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 13:58
  * </pre>
  */
object List_FirstOrder_ops {

  def main(args: Array[String]): Unit = {
    println(List(1,2,3,4) ::: List(4,5,6,7,8) ::: List(9,10,11))

    val bigData = List("Hadoop","Spark","kaffka")

    println(bigData.last)
    println(bigData.init)
    println(bigData.reverse)
    println(bigData)
    println(bigData take 2)
    println(bigData drop 2)
    println(bigData splitAt 2)
    println(bigData apply 2)
    println(bigData(2))

    val data = List('a','b','c','d','e','f')
    println(data.indices)
    println(data.indices zip data)
    println(data.zipWithIndex)
    println(data.toString())
    println(data.mkString("[",",,","]"))
    println(data.mkString("^^"))
    println(data.mkString)

    val buffer = new StringBuilder()
    data.addString(buffer,"<","**",">")
    println(buffer)
    println(data)

    val newArray = new Array[Char](10)
    data.copyToArray(newArray)
    newArray.foreach(print)
    println("=====================")

    val iterator = data.toIterator
    println(iterator.next())
    println(iterator.next())

  }

}
