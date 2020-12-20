package com.datastructures.queue

import scala.io.StdIn
import scala.util.control.Breaks.break

object CircleArrayDemo {
  def main(args: Array[String]): Unit = {

    val queue = new ArrayQueue2(4)

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

class ArrayQueue2(arrMaxSize: Int) {
  val maxSize: Int = arrMaxSize //指定队列大小
  val arr = new Array[Int](maxSize) //队列中的数据存放在数组中  数组模拟队列
  var front: Int = 0 //front 指向第一个元素  初始值0
  var rear: Int = 0 //rear 指向最后一个元素的下一个位置  初始值0


  //队列是否已满
  def isFull: Boolean = {
    (rear + 1) % maxSize == front
  }


  //队列是否为空
  def isEmpty: Boolean = {
    front == rear
  }


  //添加数据
  def addQueue(num: Int): Unit = {
    if (isFull) {
      println("队列已满 不能加入数据")
      return
    }

    arr(rear) = num
    //将rear后移
    rear = (rear + 1) % maxSize
  }


  //取出队列数据 可能取出 也可能取不出
  def getQueue: Any = {
    if (isEmpty) {
      return new Exception("队列空 没有数据")
    }

    //front指向第一个变量
    val res = arr(front)
    front = (front + 1) % maxSize
    res
  }


  //遍历显示队列
  //1. 从front开始打印 打印多少个元素
  //2. 统计出该队列有多少个有效元素
  def showQueue(): Unit = {
    if (isEmpty) {
      println("队列为空")
      return
    }

    for (i <- front until front + size()) {
      printf("arr(%d)=%d \t", i % maxSize, arr(i % maxSize))
    }

  }

  //编写一个方法 统计当前有多少个元素
  def size(): Int = {
    (rear + maxSize - front) % maxSize
  }


  //查看头元素 不取出
  def peek: Any = {
    if (isEmpty) {
      return new Exception("队列空 没有数据")
    }
    arr(front)
  }

}