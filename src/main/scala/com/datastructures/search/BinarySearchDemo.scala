package com.datastructures.search

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

object BinarySearchDemo {
  def main(args: Array[String]): Unit = {

    val arr = Array(1, 1, 1, 8, 9, 11, 12)
    val index = binarySearch(arr, 0, arr.length - 1, 12)
    if (index != -1) {
      println("找到数据 下表为 " + index)
    } else {
      println("没有找到该数据")
    }

    val resArr = binarySearchEx(arr, 0, arr.length - 1, 1)
    if (resArr.nonEmpty) {
      for (index <- resArr) {
        println("找到的索引有" + index)
      }
    } else {
      println("没有该数据")
    }

  }

  //如果存在该值就返回其下标 没有则返回-1
  @tailrec
  def binarySearch(arr: Array[Int], left: Int, right: Int, findVal: Int): Int = {

    if (left > right) {
      return -1
    }

    val midIndex = (left + right) / 2
    val midVal = arr(midIndex)
    if (midVal > findVal) {
      binarySearch(arr, left, midIndex - 1, findVal)
    } else if (midVal < findVal) {
      binarySearch(arr, midIndex + 1, right, findVal)
    } else {
      midIndex
    }
  }

  //返回相同数值的所有下标
  @tailrec
  def binarySearchEx(arr: Array[Int], left: Int, right: Int, findVal: Int): ArrayBuffer[Int] = {

    if (left > right) {
      return ArrayBuffer()
    }

    val midIndex = (left + right) / 2
    val midVal = arr(midIndex)
    if (midVal > findVal) {
      binarySearchEx(arr, left, midIndex - 1, findVal)
    } else if (midVal < findVal) {
      binarySearchEx(arr, midIndex + 1, right, findVal)
    } else {
      //定义一个可变数组
      val resArr = ArrayBuffer[Int]()
      //向左扫描
      var temp = midIndex - 1
      breakable {
        while (true) {
          if (temp < 0 || arr(temp) != findVal) {
            break()
          }
          if (arr(temp) == findVal) {
            resArr.append(temp)
          }
          temp -= 1
        }
      }
      //将中间索引加入
      resArr.append(midIndex)
      //向右扫描
      temp = midIndex + 1
      breakable {
        while (true) {
          if (temp > arr.length - 1 || arr(temp) != findVal) {
            break()
          }
          if (arr(temp) == findVal) {
            resArr.append(temp)
          }
          temp += 1
        }
      }
      resArr
    }
  }


}
