package com.datastructures.sort

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Random

//8w条数据排序需要2秒左右的时间
object SelectSortDemo {
  def main(args: Array[String]): Unit = {
    //    val arr = Array(101, 34, 119, 1)

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

    selectSort(arr)

    println("\n===============================排序后===============================")
    val now2 = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后的时间= " + date2)

  }

  def selectSort(arr: Array[Int]): Unit = {
    for (i <- 0 until arr.length - 1) {
      var min = arr(i)
      var minIndex = i
      for (j <- i + 1 until arr.length) {
        if (min > arr(j)) {
          min = arr(j)
          minIndex = j
        }
      }
      if (minIndex != i) {
        arr(minIndex) = arr(i)
        arr(i) = min
      }

      //      println(s"第${i + 1}轮结束")
      //      println(arr.mkString(","))

    }
  }
}
