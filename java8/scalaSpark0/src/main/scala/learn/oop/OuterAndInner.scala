package learn.oop

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 19:25
  * </pre>
  */
class Outer(val name: String){outter =>
  class Inner(val name: String){
    def foo(b:Inner) = println("Outer: " + outter.name + "  Inner:" + b.name)
  }
}

object OuterAndInner {

  def main(args: Array[String]): Unit = {
    val outer1 = new Outer("Spark")
    val outer2 = new Outer("Hadoop")
    val inner1 = new outer1.Inner("Scala")
    val inner2 = new outer2.Inner("Java")
    inner1.foo(inner1)
    inner2.foo(inner2)
  }


}
