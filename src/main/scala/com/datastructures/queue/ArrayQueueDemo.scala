package com.datastructures.queue

import scala.io.StdIn
import scala.util.control.Breaks.break

object ArrayQueueDemo {
  def main(args: Array[String]): Unit = {

    val queue = new ArrayQueue(4)

    var key = ""

    while (true) {

      println()
      println("请选择:")
      println("show: 显示队列")
      println("add: 添加数据")
      println("get: 取出数据")
      println("peek: 获取头元素")
      println("exit: 退出队列")

      key = StdIn.readLine()
      key match {
        case "show" => queue.showQueue()

        case "add" =>
          println("输入一个数")
          val num = StdIn.readInt()
          queue.addQueue(num)


        case "get" =>
          //对取回的值进行判断
          val res = queue.getQueue
          //如果是异常
          if (res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          } else printf("队列取出的值 = %d", res)


        case "peek" =>
          val peek = queue.peek
          if (peek.isInstanceOf[Exception]) {
            println(peek.asInstanceOf[Exception].getMessage)
          } else printf("队列的头元素 = %d", peek)


        case "exit" =>
          println("再见!")
          break


        case _ => throw new Exception("无法识别")


      }

    }


  }
}

//创建  -  遍历  -  修改  -  删除
class ArrayQueue(arrMaxSize: Int) {
  val maxSize: Int = arrMaxSize //指定队列大小
  val arr = new Array[Int](maxSize) //队列中的数据存放在数组中  数组模拟队列
  var front: Int = -1 //front表示队列的头部 但是不包含头元素  初始值-1
  var rear: Int = -1 //rear表示队列尾部 包含最后一个元素  初始值-1

  //队列是否已满
  def isFull: Boolean = {
    rear == maxSize - 1
  }

  //队列是否为空
  def isEmpty: Boolean = {
    front == rear
  }

  //向队列添加数据
  def addQueue(num: Int): Unit = {
    if (isFull) {
      println("队列已满 不能加入数据")
      return
    }
    //将rear后移
    rear += 1
    arr(rear) = num

  }

  //遍历显示队列
  def showQueue(): Unit = {
    if (isEmpty) {
      println("队列为空")
      return
    }

    //遍历
    println("队列数据情况是")
    for (i <- (front + 1) to rear) {
      printf("arr(%d)=%d \t", i, arr(i))
    }
  }

  //取出队列数据 可能取出 也可能取不出
  def getQueue: Any = {
    if (isEmpty) {
      return new Exception("队列空 没有数据")
    }

    //将front后移
    front += 1
    arr(front)
  }

  //查看头元素 不取出
  def peek: Any = {
    if (isEmpty) {
      return new Exception("队列空 没有数据")
    }
    arr(front + 1)
  }


}
