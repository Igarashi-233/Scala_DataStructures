package com.datastructures.sort

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Random

//8000w条数据排序需要12秒左右
object MergeSortDemo {
  def main(args: Array[String]): Unit = {

    //    val arr = Array(1, 9, 2, 3, 66, 7, 3)
    //    val temp = new Array[Int](arr.length)
    //    mergeSort(arr, 0, arr.length - 1, temp)
    //    println("排序后")
    //    println(arr.mkString(","))


    //创建一个80000个随机数据的数组
    val random = new Random()
    val arr = new Array[Int](80000000)
    val temp = new Array[Int](80000000)

    for (i <- 0 until 80000000) {
      arr(i) = random.nextInt(8000000)
    }

    println("===============================排序前===============================")
    val now = new Date()
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前的时间= " + date)

    mergeSort(arr, 0, arr.length - 1, temp)

    println("\n===============================排序后===============================")
    val now2 = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后的时间= " + date2)

  }

  /**
   *
   * @param arr   待排序数组
   * @param left  最初的左边数据的下标 0
   * @param right 最初的右边的下标 length-1
   * @param temp  临时数组
   */
  def mergeSort(arr: Array[Int], left: Int, right: Int, temp: Array[Int]): Unit = {
    if (left < right) {
      val mid = (left + right) / 2
      mergeSort(arr, left, mid, temp) //递归将左侧数据排好序
      mergeSort(arr, mid + 1, right, temp) //递归将右侧数据排好序
      //合并操作
      merge(arr, left, mid, right, temp)
    }
  }

  def merge(arr: Array[Int], left: Int, mid: Int, right: Int, temp: Array[Int]): Unit = {

    var i = left //左边指针
    var j = mid + 1 //右边指针
    var t = 0 //temp数组的索引

    while (i <= mid && j <= right) {
      if (arr(i) <= arr(j)) {
        temp(t) = arr(i)
        t += 1
        i += 1
      } else {
        temp(t) = arr(j)
        t += 1
        j += 1
      }
    }

    while (i <= mid) {
      temp(t) = arr(i)
      t += 1
      i += 1
    }

    while (right >= j) {
      temp(t) = arr(j)
      t += 1
      j += 1
    }

    t = 0
    var tempLeft = left
    while (tempLeft <= right) {
      arr(tempLeft) = temp(t)
      t += 1
      tempLeft += 1
    }
  }
}
