package com.datastructures.linkedlist

import scala.util.control.Breaks.{break, breakable}

object DoubleLinkedListDemo {
  def main(args: Array[String]): Unit = {

    val doubleLinkedList = new DoubleLinkedList()
    //创建四个英雄
    val Node1 = new HeroNode2(1, "宋江", "及时雨")
    val Node2 = new HeroNode2(2, "卢俊义", "玉麒麟")
    val Node3 = new HeroNode2(3, "吴用", "智多星")
    val Node4 = new HeroNode2(4, "行者", "武松")

    doubleLinkedList.add(Node1)
    doubleLinkedList.add(Node2)
    doubleLinkedList.add(Node3)
    doubleLinkedList.add(Node4)

    println(" 正向 双向链表情况")
    doubleLinkedList.list()
    println("\n\n 逆向 双向链表情况")
    doubleLinkedList.reverselist()


    val Node5 = new HeroNode2(4, "公孙胜", "入云龙")
    doubleLinkedList.update(Node5)

    println("\n\n 正向 双向链表修改后的情况")
    doubleLinkedList.list()
    println("\n\n 逆向 双向链表修改后的情况")
    doubleLinkedList.reverselist()


    //删除节点  (删除第一个和最后一个)
    //    println("\n\n 正向 删除双向链表第一个节点后的情况")
    //    doubleLinkedList.delete(1)
    //    doubleLinkedList.list()
    //    println("\n\n 逆向 删除双向链表第一个节点后的情况")
    //    doubleLinkedList.reverselist()

    println("\n\n 正向 删除链表最后一个节点后的情况")
    doubleLinkedList.delete(4)
    doubleLinkedList.list()
    println("\n\n 逆向 删除链表最后一个节点后的情况")
    doubleLinkedList.reverselist()

    //    println("\n\n 正向 链表清空后的情况")
    //    doubleLinkedList.delete(1)
    //    doubleLinkedList.delete(2)
    //    doubleLinkedList.delete(3)
    //    doubleLinkedList.delete(4)
    //    doubleLinkedList.list()
    //    println("\n\n 逆向 链表清空后的情况")
    //    doubleLinkedList.reverselist()


    //另外创建4个 no 无顺序的英雄
    val Node7 = new HeroNode2(5, "关胜", "大刀")
    val Node9 = new HeroNode2(6, "林冲", "豹子头")
    val Node6 = new HeroNode2(7, "秦明", "霹雳火")
    val Node8 = new HeroNode2(8, "呼延灼", "双鞭")

    doubleLinkedList.addByOrder(Node6)
    doubleLinkedList.addByOrder(Node7)
    doubleLinkedList.addByOrder(Node8)
    //    doubleLinkedList.addByOrder(Node8)
    doubleLinkedList.addByOrder(Node9)

    println("\n\n 正向 再次添加后的链表情况")
    doubleLinkedList.list()
    println("\n\n 逆向 再次添加后的链表情况")
    doubleLinkedList.reverselist()

  }
}

//添加 遍历 修改 删除
class DoubleLinkedList {
  //创建头节点 指向链表头部
  val head = new HeroNode2(-1, "", "")
  //创建尾节点 指向链表尾部
  val tail = new HeroNode2(Int.MaxValue, "", "")

  //初始化状态
  head.next = tail
  tail.pre = head

  //删除节点 (双向链表可以实现自我删除 可以直接将temp指向要删除的节点)
  def delete(no: Int): Unit = {
    if (isEmpty) {
      println("链表空")
      return
    }

    var temp = head.next
    var flag = false
    breakable {
      while (true) {
        if (temp.no == no) {
          flag = true
          break()
        }

        if (temp.next == null) {
          break()
        }

        temp = temp.next
      }
    }

    if (flag) {
      //删除
      temp.pre.next = temp.next
      if (temp.next != null) {
        temp.next.pre = temp.pre
      }
    } else {
      println("要删除的no=%d不存在", no)
    }

  }


  //修改节点信息
  def update(heroNode: HeroNode2): Unit = {
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


  //正向遍历双向链表
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
        if (temp.next == tail) {
          break()
        }

        //后移
        temp = temp.next
      }
    }

  }


  //逆向遍历双向链表
  def reverselist(): Unit = {
    if (isEmpty) {
      println("链表空")
      return
    }

    var temp = tail.pre

    breakable {
      while (true) {
        //输出当前节点信息
        printf("no=%d  name=%s  nickname=%s  -->  ", temp.no, temp.name, temp.nickname)

        if (temp.pre == head) {
          break()
        }

        //前移
        temp = temp.pre
      }
    }
  }

  def isEmpty: Boolean = {
    head.next == null
  }


  //基础添加
  //默认加入链表最后 不按照 no 顺序
  def add(heroNode: HeroNode2): Unit = {
    //1.找到链表最后节点
    //2.最后节点的next = 新的节点

    //head不动  使用一个辅助指针完成定位
    var temp = head

    breakable {
      while (true) {
        if (temp.next == tail) {
          break()
        }
        temp = temp.next
      }
    }
    //while退出时 temp指向最后
    temp.next = heroNode
    heroNode.next = tail
    tail.pre = heroNode
    heroNode.pre = temp //双向链表
  }


  //顺序添加
  def addByOrder(heroNode2: HeroNode2): Unit = {
    var temp = head

    var flag = false

    breakable {
      while (true) {

        if (temp.next == tail) {
          break()
        }

        if (temp.next.no == heroNode2.no) {
          flag = true
        } else if (temp.next.no > heroNode2.no) {
          break()
        }

        temp = temp.next
      }
    }

    if (flag) { //已经存在no
      println("\n")
      print("已经存在 no=%d 的人物", heroNode2.no)
    } else {
      heroNode2.next = temp.next
      temp.next.pre = heroNode2
      temp.next = heroNode2
      heroNode2.pre = temp
    }


  }

}

class HeroNode2(hNo: Int, hName: String, nNickname: String) {
  val no: Int = hNo
  var name: String = hName
  var nickname: String = nNickname
  var next: HeroNode2 = _ //下一个节点
  var pre: HeroNode2 = _ //前一个节点
}
