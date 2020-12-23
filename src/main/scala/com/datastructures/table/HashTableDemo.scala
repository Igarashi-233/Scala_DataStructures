package com.datastructures.table

import util.control.Breaks._

object HashTableDemo {
  def main(args: Array[String]): Unit = {
    //测试一下哈希表
    val hashTable = new HashTable(5)
    //创建Emp
    val emp1 = new Emp(5, "aa")
    val emp2 = new Emp(10, "bb")
    val emp3 = new Emp(15, "cc")
    val emp4 = new Emp(20, "dd")
    val emp5 = new Emp(6, "ee")
    val emp6 = new Emp(11, "ff")
    val emp7 = new Emp(16, "gg")
    val emp8 = new Emp(21, "hh")
    val emp9 = new Emp(24, "jj")
    hashTable.addEmp(emp1)
    hashTable.addEmp(emp2)
    hashTable.addEmp(emp3)
    hashTable.addEmp(emp4)
    hashTable.addEmp(emp5)
    hashTable.addEmp(emp6)
    hashTable.addEmp(emp7)
    hashTable.addEmp(emp8)
    hashTable.addEmp(emp9)
    hashTable.list()

    //测试一下
    hashTable.findEmpById(115)
  }
}


//编写HashTable 类
class HashTable(size: Int) {
  //维护一个 EmpLinkedList 数组
  val empLinkedListArr = new Array[EmpLinkedList](size)
  //需要将每个链表new
  for (i <- 0 until size) {
    empLinkedListArr(i) = new EmpLinkedList()
  }

  //查找一个雇员时
  def findEmpById(no: Int): Unit = {
    //先计算该no对应的链表
    val empLinkedListNo = hash(no)
    val emp = empLinkedListArr(empLinkedListNo).findEmpByNo(no)
    if (emp != null) {
      println()
      printf("找到 no =%d name = %s", no, emp.name)
    } else {
      println()
      println("没有找到该雇员=" + no)
    }
  }

  //添加
  def addEmp(emp: Emp): Unit = {
    val empLinkedListNo = hash(emp.no)
    empLinkedListArr(empLinkedListNo).addEmp(emp)
  }

  //遍历hashtable
  def list(): Unit = {
    for (i <- 0 until size) {
      empLinkedListArr(i).list(i)
    }
  }

  //编写一个hash方法(散列函数)
  def hash(no: Int): Int = {
    no % size
  }
}

//Emp类
class Emp(empNO: Int, eName: String) {
  val no: Int = empNO
  var name: String = eName
  var next: Emp = _

}

//编写EmpLinkedList, 存放的就是雇员信息
class EmpLinkedList {
  var head: Emp = _

  //查询一个雇员
  /**
   *
   * @param no 要查找的雇员的no
   * @return 如果找到，返回Emp, 否则null
   */
  def findEmpByNo(no: Int): Emp = {
    if (head == null) {
      //println("没有找到")
      return null
    }
    //辅助执行
    var curEmp = head
    breakable {
      while (true) {
        if (curEmp.no == no) {
          break() //curEmp 就指向了查找的Emp
        }
        if (curEmp.next == null) {
          curEmp = null
          break()
        }
        curEmp = curEmp.next
      }
    }
    curEmp

  }

  //添加雇员
  //直接加入到链表最后
  def addEmp(emp: Emp): Unit = {
    if (head == null) {
      head = emp
    } else {
      var curEmp = head
      //将curEmp 定位到链表的最后
      breakable {
        while (true) {
          if (curEmp.next == null) {
            break()
          }
          curEmp = curEmp.next
        }
      }
      curEmp.next = emp
    }
  }

  //遍历该链表
  def list(no: Int): Unit = {
    //判断链表是否为null
    if (head == null) {
      println()
      println("第" + no + "链表情况 ->")
      return
    }
    //使用辅助指针
    var curEmp = head
    println()
    print("第" + no + "链表情况->")
    breakable {
      while (true) {
        printf("no=%d name=%s->", curEmp.no, curEmp.name)
        if (curEmp.next == null) {
          break()
        }
        curEmp = curEmp.next
      }
    }
  }
}
