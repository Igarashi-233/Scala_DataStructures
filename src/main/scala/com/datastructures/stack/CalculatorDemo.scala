package com.datastructures.stack

import scala.util.control.Breaks.{break, breakable}

object CalculatorDemo {
  def main(args: Array[String]): Unit = {

    val expression = "30+6*3-9"

    //数栈
    val numStack = new CalculatorStack(10)
    //符号栈
    val opeStack = new CalculatorStack(10)

    var index = 0
    var num1 = 0
    var num2 = 0
    var oper = 0
    var res = 0
    var ch = ' '
    var keepNum = "" //扫描时 保存上次的数字ch 并进行拼接

    breakable {
      while (true) {
        ch = expression.substring(index, index + 1)(0)
        if (opeStack.isOper(ch)) { //如果是操作符

          if (!opeStack.isEmpty) {

            //当前符号优先级 小于或等于符号栈栈顶符号优先级
            if (opeStack.priority(ch) <= opeStack.priority(opeStack.stack(opeStack.top))) {
              //弹出数栈两个数据 进行运算
              num1 = numStack.pop().toString.toInt
              num2 = numStack.pop().toString.toInt

              oper = opeStack.pop().toString.toInt

              res = numStack.cal(num1, num2, oper)

              //再次入栈
              numStack.push(res)

              opeStack.push(ch)

            } else {
              //当前符号优先级 大于符号栈栈顶符号优先级
              opeStack.push(ch)
            }

          } else {
            opeStack.push(ch)
          }

        } else { //如果是数字

          //多位数处理
          keepNum += ch

          if(index == expression.length-1){
            numStack.push(keepNum.toInt)
          }else{
            // 如果是数字 等待下一次扫描并拼接   如果是操作符 则直接入数栈
            if (opeStack.isOper(expression.substring(index + 1, index + 2)(0))) {
              numStack.push(keepNum.toInt)
              keepNum = ""
            }
          }
          //          numStack.push((ch + "").toInt)
        }

        //index后移
        index += 1
        if (index >= expression.length) {
          break()
        }

      }
    }

    breakable {
      while (true) {
        if (opeStack.isEmpty) {
          break()
        }

        num1 = numStack.pop().toString.toInt
        num2 = numStack.pop().toString.toInt
        oper = opeStack.pop().toString.toInt
        res = numStack.cal(num1, num2, oper)

        numStack.push(res)

      }
    }

    val result = numStack.pop()
    printf("表达式 %s = %d", expression, result)

  }
}

class CalculatorStack(size: Int) {
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


  //自定义返回运算符的优先级
  // + - => 0     * / => 1
  def priority(oper: Int): Int = {

    if (oper == '*' || oper == '/') {
      1
    } else if (oper == '-' || oper == '+') {
      0
    } else {
      -1
    }

  }

  //操作符判断
  def isOper(value: Int): Boolean = {
    value == '+' || value == '-' || value == '*' || value == '/'
  }


  //计算方法
  def cal(num1: Int, num2: Int, oper: Int): Int = {
    var res = 0
    oper match {
      case '+' => res = num1 + num2
      case '-' => res = num2 - num1
      case '*' => res = num1 * num2
      case '/' => res = num2 / num1
    }
    res
  }


}