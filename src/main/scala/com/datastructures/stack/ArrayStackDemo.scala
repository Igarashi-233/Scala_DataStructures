package com.datastructures.stack

import scala.io.StdIn

object ArrayStackDemo {
  def main(args: Array[String]): Unit = {

    val stack = new ArrayStack(7)

    var key = ""

    while (true) {

      println("")
      println("请选择:")
      println("show: 显示队列")
      println("push: 添加数据")
      println("pop: 取出数据")
      println("exit: 退出队列")

      key = StdIn.readLine()
      key match {
        case "show" => stack.list()
        case "push" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          stack.push(value)
        }
        case "pop" => {
          val res = stack.pop()
          if (res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          } else {
            printf("取出的数据为 %d", res)
          }
        }
        case "exit" => {
          System.exit(0)
        }
      }

    }

  }
}

class ArrayStack(size: Int) {
  val maxSize: Int = size
  var stack = new Array[Int](maxSize)

  //栈顶初始化 -1
  var top: Int = -1

  //栈满
  def isFull: Boolean = {
    top == maxSize - 1
  }

  //栈空
  def isEmpty: Boolean = {
    top == -1
  }

  //入栈
  def push(value: Int): Unit = {
    if (isFull) {
      println("栈满")
      return
    }

    top += 1
    stack(top) = value
  }

  //出栈
  def pop(): Any = {
    if (isEmpty) {
      return new Exception("栈空")
    }
    val value = stack(top)
    top -= 1
    value

  }

  //遍历栈
  def list(): Unit = {
    if (isEmpty) {
      println("栈空 没有数据")
      return
    }

    for (i <- 0 to top reverse) {
      printf("stack[%d]= %d\n", i, stack(i))
    }

  }

}