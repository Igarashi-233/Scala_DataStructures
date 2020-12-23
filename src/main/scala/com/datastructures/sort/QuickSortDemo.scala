package com.datastructures.sort

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

//800w条数据排序需要1秒左右的时间
object QuickSortDemo {
  def main(args: Array[String]): Unit = {

    //    val arr = Array(1, 9, 2, 3, 66, 7, 3)
    //    quickSort(0, arr.length - 1, arr)
    //    println("排序后")
    //    println(arr.mkString(","))

    //创建一个80000个随机数据的数组
    val random = new Random()
    val arr = new Array[Int](8000000)

    for (i <- 0 until 8000000) {
      arr(i) = random.nextInt(8000000)
    }

    println("===============================排序前===============================")
    val now = new Date()
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("排序前的时间= " + date)

    quickSort(0, arr.length - 1, arr)

    println("\n===============================排序后===============================")
    val now2 = new Date()
    val date2 = dateFormat.format(now2)
    println("排序后的时间= " + date2)

  }

  /**
   *
   * @param left  指定从数组的左边下标
   * @param right 指定从数组右边的下标 length-1
   * @param arr   进行排序的数组
   */
  def quickSort(left: Int, right: Int, arr: Array[Int]): Unit = {
    var l = left
    var r = right
    val pivot = arr((left + right) / 2)
    var temp = 0

    breakable {
      // 将比pivot小的数据放在左边 比pivot大的数据放右边
      while (l < r) {
        while (arr(l) < pivot) { //从左边找一个比 pivot 大的值对应下标
          l += 1
        }
        while (arr(r) > pivot) { //从右边找一个比 pivot 小的值对应下标
          r -= 1
        }
        if (l >= r) { //本次交换结束
          break()
        }
        temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp


        if (arr(l) == pivot) {
          r -= 1
        }
        if (arr(r) == pivot) {
          l += 1
        }
      }
    }

    if (l == r) {
      l += 1
      r -= 1
    }
    if (left < r) { //向左再次递归
      quickSort(left, r, arr)
    }
    if (right > l) { //向右再次递归
      quickSort(l, right, arr)
    }
  }
}
