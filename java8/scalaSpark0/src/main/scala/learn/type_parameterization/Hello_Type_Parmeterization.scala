package learn.type_parameterization


import scala.reflect.ClassTag

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 19:48
  * </pre>
  */
object Hello_Type_Parmeterization {
  def main(args: Array[String]): Unit = {
    val triple = new Triple("Spark",3,3.1415)
    val bigData = new Triple[String,String,Char]("Spark","Hadoop",'R')

    def getData[T](list:List[T]) = list(list.length / 2)

    println(getData(List("Spark","Hadoop",'R')))
    val f = getData[Int] _
    println(f(List(1,2,3,4,5,6,7,8)))

    def buildArray[T: ClassTag](len: Int) = new Array[T](len)
    println(buildArray[Int](5).toList)
  }

}
