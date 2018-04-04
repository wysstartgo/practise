package learn.oop

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 19:10
  * </pre>
  */
object ObjectOps {

  def main(args: Array[String]): Unit = {
    println(University.newStudentNo)
    println(University.newStudentNo)
    println(new University().id)
    val ss = University.apply()
    println(ss.id)
  }


}

class University {
  val id = University.newStudentNo
  private var number = 0
  def aClass(number: Int): Unit = {
    this.number += number
  }
}

object University{
  /* 类的成员在该类以外为地方使用时不能定义成 private类型 */
  var studentNo = 0
  def newStudentNo = {
    studentNo += 1
    studentNo
  }

  //定义apply方法,实例化伴生类
  def apply()= new University()

}
