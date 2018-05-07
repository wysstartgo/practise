import org.apache.spark.ml.linalg.{Matrices, Vectors}

/**
  * <pre>
  *
  * 【标题】: spark的一些测试
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-28 10:52
  * </pre>
  */
object TestSpark {

  def main(args: Array[String]): Unit = {
    val A1 = (1 to 5).toArray.map{ f => f.toDouble}
    val V1 = Vectors.dense(A1)
    val V2 = Vectors.dense(2.0,2.0,2.0,2.0,2.0,2.0)


    val S1 = Vectors.sparse(5,Array(0,1,2,3,4),Array(1.0,2.0,3.0,4.0,5.0))

    val S2 = Vectors.sparse(5,Seq((0,1.0),(1,2.0),(2,3.0),(3,4.0),(4,5.0)))
    println(S2)


    val A2 = (1 to 25).toArray.map{f => f.toDouble}

    val M1 = Matrices.dense(5,5,A2);

    val M2 = Matrices.sparse(3,3,Array(0,2,3,6),Array(0,2,1,0,1,2),Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
  }

}
