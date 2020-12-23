package com.datastructures.sort

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Random

//8w条数据排序需要1秒左右的时间

object InsertSortDemo {
  def main(args: Array[String]): Unit = {
    //    val arr = Array(101, 34, 119, 1, -1, 45, 900)

    //创建一个80000个随机数据的数组
    val random = new Random()
    val arr = new Array[Int](80000)

    for (i <- 0 until 80000) {
      arr(i) = random.nextInt(8000000)
    }

    println("===============================排序前===============================")
    val now = new Date()
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前的时间= " + date)

    insertSort(arr)

    println("\n===============================排序后===============================")
    val now2 = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后的时间= " + date2)


  }

  def insertSort(arr: Array[Int]): Unit = {
    for (i <- 1 until arr.length) {

      val insertVal = arr(i)
      var insertIndex = i - 1
      while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
        arr(insertIndex + 1) = arr(insertIndex)
        insertIndex -= 1
      }
      arr(insertIndex + 1) = insertVal

      //      println(s"第${i}轮结果=" + arr.mkString(","))
    }
  }
}
