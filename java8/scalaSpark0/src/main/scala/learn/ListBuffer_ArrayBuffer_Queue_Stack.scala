package learn

import scala.collection.immutable.Queue
import scala.collection.mutable


/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 15:56
  * </pre>
  */
object ListBuffer_ArrayBuffer_Queue_Stack {

  def main(args: Array[String]): Unit = {
    import scala.collection.mutable.ListBuffer

    val listBuffer = new ListBuffer[Int]
    listBuffer += 1
    listBuffer += 2
    println(listBuffer)

    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = new ArrayBuffer[Int]
    arrayBuffer += 1
    arrayBuffer += 2
    println(arrayBuffer)

    val empty = Queue[Int]()

    val queue1 = empty.enqueue(1)
    val queue2 = queue1.enqueue(List(2,3,4,5))
    println(queue2)
    val (element,left) = queue2.dequeue
    println(element + ":" + left)

    val queue = new mutable.Queue[String]()
    queue += "a"
    queue ++= List("b","c")
    println(queue)
    println(queue.dequeue())
    println(queue)

    val stack = new mutable.Stack[Int]()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    println(stack.top)
    println(stack)
    println(stack.pop)
    println(stack)
  }

}
