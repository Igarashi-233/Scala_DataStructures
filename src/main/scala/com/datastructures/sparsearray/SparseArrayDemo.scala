package com.datastructures.sparsearray

import scala.collection.mutable.ArrayBuffer

object SparseArrayDemo {

  def main(args: Array[String]): Unit = {

    //二维数组映射棋盘
    val rows = 11
    val cols = 11
    var count = 0
    val chessMap1 = Array.ofDim[Int](rows, cols)

    //初始化
    chessMap1(1)(2) = 1
    chessMap1(2)(3) = 2

    chessMap1(3)(5) = 2
    chessMap1(4)(8) = 1

    println("原始棋盘")

    for (row <- chessMap1) {
      for (item <- row) {
        printf("%d  ", item)
      }
      //换行
      println()
    }

    //对原始二维数组进行压缩
    //1. 创建ArrayBuffer  动态添加数据
    //2. 使用Node对象 表示一行数据

    val sparseAray = ArrayBuffer[Node]()
    //将第一行数据放入
    sparseAray.append(new Node(rows, cols, 0))
    //遍历chessMap1  给非0值创建对象 并加入到sparseArray中
    for (i <- 0 until chessMap1.length) {
      for (j <- 0 until chessMap1(i).length) {
        if (chessMap1(i)(j) != 0) {
          //有效数据 需要保存
          count += 1
          sparseAray.append(new Node(i, j, chessMap1(i)(j)))
        }
      }
    }
    sparseAray.update(0, new Node(rows, cols, count))

    println("稀疏数组")

    for (i <- 0 until sparseAray.length) {
      val node = sparseAray(i)
      printf("%d %d %d\n", node.row, node.col, node.value)
    }


    //将稀疏数组恢复为原始棋盘
    //1. 读取稀疏数组的第一行 创建一个二位棋盘
    //2. 遍历稀疏数组 读取每一个Node 恢复到chessMap2
    val node = sparseAray(0)
    val chessMap2 = Array.ofDim[Int](node.row, node.col)
    for (i <- 1 until sparseAray.length) {
      val node1 = sparseAray(i)
      chessMap2(node1.row)(node1.col) = node1.value
    }

    println("恢复后的棋盘")

    for (row <- chessMap2) {
      for (item <- row) {
        printf("%d  ", item)
      }
      println()
    }


  }
}

class Node(val row: Int, val col: Int, val value: Int)
