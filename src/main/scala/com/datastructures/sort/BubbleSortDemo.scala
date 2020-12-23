package com.datastructures.sort

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Random

//8w条数据排序需要10秒左右的时间

object BubbleSortDemo {
  def main(args: Array[String]): Unit = {
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

    //    println(arr.mkString(""))
    println("\n===============================排序后===============================")
    bubbleSort(arr)
    //    println(arr.mkString(""))
    val now2 = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后的时间= " + date2)


  }

  def bubbleSort(arr: Array[Int]): Unit = {
    for (i <- 0 until arr.length - 1) {
      for (j <- 0 until arr.length - 1) {
        if (arr(j) > arr(j + 1)) {
          val temp = arr(j)
          arr(j) = arr(j + 1)
          arr(j + 1) = temp
        }
      }
    }
  }

}
