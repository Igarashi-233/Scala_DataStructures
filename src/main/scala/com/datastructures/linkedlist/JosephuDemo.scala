package com.datastructures.linkedlist

import scala.util.control.Breaks.{break, breakable}

object JosephuDemo {
  def main(args: Array[String]): Unit = {

    val josephu = new Josephu
    josephu.addBoy(7)
    josephu.showBoy()
    josephu.countBoy(4, 3, 7)

  }
}

class Josephu {
  //头节点
  var first: Boy = new Boy(-1)

  //添加
  def addBoy(nums: Int): Unit = {
    if (nums < 1) {
      println("nums值不正确")
      return
    }

    //辅助指针
    var curBoy: Boy = null

    for (no <- 1 to nums) {
      val boy = new Boy(no)

      if (no == 1) {
        first = boy
        first.next = first //只有一个值的环形链表
        curBoy = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }

  }

  //数数 CountBoy 完成游戏规则
  //startNo 从第几个开始数   countNum 数几下   nums 总共多少人
  def countBoy(startNo: Int, countNum: Int, nums: Int): Unit = {

    if (first.next == null || startNo < 1 || countNum > nums) {
      println("参数有误 无法完成游戏")
      return
    }

    //辅助指针 指向第一个节点的前一个位置
    var helper = first

    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }

    //将first指针移动到startNo  helper也对应移动到移动后first的前一个位置
    for (i <- 0 until startNo - 1) {
      first = first.next
      helper = helper.next
    }

    breakable {
      while (true) {

        //只有一个人时结束
        if (helper == first) {
          break()
        }

        //数数 countNum 的个数
        for (i <- 0 until countNum - 1) {
          first = first.next
          helper = helper.next
        }

        //输出出圈人信息
        printf("小孩%d出圈\n", first.no)

        //删除节点
        helper.next = first.next
        first = first.next

      }
    }
    printf("最后剩下的是编号为%d的小孩\n", first.no)

  }

  //遍历
  def showBoy(): Unit = {
    if (first.next == null) {
      println("没有小孩~~")
      return
    }

    //辅助指针  初始时指向第一个节点
    var cur = first

    breakable {
      while (true) {
        printf("小孩编号为 %d\n", cur.no)

        if (cur.next == first) {
          break()
        }

        cur = cur.next
      }
    }

  }


}

class Boy(boyNo: Int) {
  val no: Int = boyNo
  var next: Boy = _
}