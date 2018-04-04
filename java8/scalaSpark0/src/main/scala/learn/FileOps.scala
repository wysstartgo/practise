package learn

import java.io.PrintWriter

import scala.io.{Source, StdIn}

/**
  * <pre>
  *
  * 【标题】: 文件操作
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 11:00
  * </pre>
  */
object FileOps {

  def main(args: Array[String]): Unit = {


  }

  def readFile(): Unit = {
    val file = Source.fromFile("E:\\admin.txt")
    for(line <- file.getLines()){
      println(line)
    }
    file.close()
  }

  def readUrl(): Unit = {
    val webFile = Source.fromURL("http://blog.sina.com.cn/s/blog_535aa0930100bo3c.html");
    webFile.foreach(print)
    webFile.close()
  }

  def writeFile(content : String): Unit = {
    val writer = new PrintWriter("E:\\admin.txt")
    writer.println(content)
    writer.close()
  }

  def readConsole(): Unit = {
    print("Please enter your input: ")
    val line = StdIn.readLine()
    println("Thanks you just typed:" + line)
  }


}
