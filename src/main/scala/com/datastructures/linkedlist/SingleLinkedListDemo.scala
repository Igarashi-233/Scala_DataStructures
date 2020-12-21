package com.datastructures.linkedlist

import scala.util.control.Breaks.{break, breakable}

object SingleLinkedListDemo {
  def main(args: Array[String]): Unit = {

    val singleLinkedList = new SingleLinkedList()
    //创建四个英雄
    val Node1 = new HeroNode(1, "宋江", "及时雨")
    val Node2 = new HeroNode(2, "卢俊义", "玉麒麟")
    val Node3 = new HeroNode(3, "吴用", "智多星")
    val Node4 = new HeroNode(4, "行者", "武松")

    singleLinkedList.add(Node1)
    singleLinkedList.add(Node2)
    singleLinkedList.add(Node3)
    singleLinkedList.add(Node4)

    println("链表情况")
    singleLinkedList.list()


    //修改英雄信息
    val Node5 = new HeroNode(4, "公孙胜", "入云龙")
    singleLinkedList.update(Node5)

    println("\n\n链表修改后的情况")
    singleLinkedList.list()


    //删除节点  (删除第一个和最后一个)
    println("\n\n删除链表第一个节点后的情况")
    singleLinkedList.delete(1)
    singleLinkedList.list()
    //    println("\n\n删除链表最后一个节点后的情况")
    //    singleLinkedList.delete(4)
    //    singleLinkedList.list()

    //    println("\n\n链表清空后的情况")
    //    singleLinkedList.delete(1)
    //    singleLinkedList.delete(2)
    //    singleLinkedList.delete(3)
    //    singleLinkedList.delete(4)
    //    singleLinkedList.list()


    //另外创建4个 no 无顺序的英雄
    val Node7 = new HeroNode(5, "关胜", "大刀")
    val Node9 = new HeroNode(6, "林冲", "豹子头")
    val Node6 = new HeroNode(7, "秦明", "霹雳火")
    val Node8 = new HeroNode(8, "呼延灼", "双鞭")

    singleLinkedList.addByOrder(Node6)
    singleLinkedList.addByOrder(Node7)
    singleLinkedList.addByOrder(Node8)
    //    singleLinkedList.addByOrder(Node8)
    singleLinkedList.addByOrder(Node9)

    println("\n\n再次添加后的链表情况")
    singleLinkedList.list()


  }
}

//创建单链表类
class SingleLinkedList {
  //创建头节点 指向链表头部
  val head = new HeroNode(-1, "", "")


  //删除节点
  def delete(no: Int): Unit = {
    if (isEmpty) {
      println("链表空")
      return
    }

    //为了删除第一个节点 temp指向不会使用的head
    var temp = head
    //标记
    var flag = false

    breakable {
      while (true) {
        if (temp.next.no == no) {
          flag = true
          break()
        }

        //判断temp是否指向链表倒数第二个节点
        if (temp.next.next == null) {
          break()
        }

        temp = temp.next

      }
    }

    if (flag) {
      temp.next = temp.next.next
    } else {
      println("删除的节点 %d 不存在", no)
    }


  }


  //修改节点信息
  def update(heroNode: HeroNode): Unit = {
    if (isEmpty) {
      println("链表空")
      return
    }

    var temp = head.next
    //定义一个变量标识是否找到该节点
    var flag = false
    breakable {
      while (true) {
        //比较
        if (temp.no == heroNode.no) {
          flag = true
          break()
        }

        //temp是否到最后节点
        if (temp.next == null) {
          break()
        }

        //后移
        temp = temp.next
      }
    }

    if (flag) { //找到
      temp.nickname = heroNode.nickname
      temp.name = heroNode.name
    } else { //未找到
      printf("想要修改的%d英雄不存在.", heroNode.no)
    }


  }


  //顺序添加
  def addByOrder(heroNode: HeroNode): Unit = {
    var temp = head

    //是否已经存在该编号的Node
    var flag = false

    breakable {
      while (true) {

        //需要先判断
        if (temp.next == null) {
          break()
        }

        if (temp.next.no == heroNode.no) { //no已经存在
          flag = true
        } else if (temp.no < heroNode.no && temp.next.no > heroNode.no) {
          break()
        }

        temp = temp.next
      }
    }
    if (flag) { //已经存在no
      println("已经存在 no=%d 的人物", heroNode.no)
    } else {
      heroNode.next = temp.next
      temp.next = heroNode
    }

  }


  //基础添加
  //默认加入链表最后 不按照 no 顺序
  def add(heroNode: HeroNode): Unit = {
    //1.找到链表最后节点
    //2.最后节点的next = 新的节点

    //head不动  使用一个辅助指针完成定位
    var temp = head

    breakable {
      while (true) {
        if (temp.next == null) {
          break()
        }
        temp = temp.next
      }
    }
    //while退出时 temp指向最后
    temp.next = heroNode
  }


  //遍历单向链表
  def list(): Unit = {
    if (isEmpty) {
      println("链表空 不能遍历")
      return
    }

    //有效数据不包括head
    var temp = head.next

    breakable {
      while (true) {
        //输出当前节点信息
        printf("no=%d  name=%s  nickname=%s  -->  ", temp.no, temp.name, temp.nickname)

        //是否为最后节点
        if (temp.next == null) {
          break()
        }

        //后移
        temp = temp.next
      }
    }

  }

  def isEmpty: Boolean = {
    head.next == null
  }

}

class HeroNode(hNo: Int, hName: String, nNickname: String) {
  val no: Int = hNo
  var name: String = hName
  var nickname: String = nNickname
  var next: HeroNode = _
}