package learn.type_parameterization

import scala.reflect.ClassTag

/**
  * <pre>
  *
  * 【标题】: https://blog.csdn.net/dax1n/article/details/77036447
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-04 9:09
  * </pre>
  */
class A[T];

object Manifest_ClassTag {

  def main(args: Array[String]): Unit = {

    /**
      *
      * @param first
      * @param second
      * @tparam T
      * @return
      */
    def arrayMake[T: Manifest](first: T,second: T) = {
      val r = new Array[T](2)
      r(0) = first
      r(1) = second
      r
    }

    arrayMake(1,2).foreach(println)

    def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

    mkArray(42,13).foreach(println)

    mkArray("Japan","Brazil","Germany").foreach(println)

    def manif[T](x: List[T])(implicit m:Manifest[T]) = {
      if(m <:< manifest[String]){
        println("List Strings")
      }else{
        println("Some other type")
      }
    }

    manif(List("Spark","Hadoop"))
    manif(List(2,4))
    manif(List("Scala",3))

    val m = manifest[A[String]]

    println(m)

    val cm = classManifest[A[String]]
    println(cm)
  }

}
