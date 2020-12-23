package com.datastructures.recursive

object MazeDemo {
  def main(args: Array[String]): Unit = {

    //地图绘制
    val map = Array.ofDim[Int](8, 7)
    //上下左右全部置为1
    for (i <- 0 until 7) {
      map(0)(i) = 1
      map(7)(i) = 1
    }
    for (i <- 0 until 8) {
      map(i)(0) = 1
      map(i)(6) = 1
    }

    //设置挡板
    map(3)(1) = 1
    map(3)(2) = 1
    map(2)(2) = 1

    //打印地图
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(map(i)(j) + "\t")
      }
      println()
    }

    println("\n============================新地图==============================\n")

    //测试
    setWay(map, 1, 1)
    //打印新地图
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(map(i)(j) + "\t")
      }
      println()
    }

  }

  // 使用递归回溯找路
  // map为地图
  // i j指定从地图的哪个点开始出发
  // 地图元素为 0 表示可以走且还没有走
  //          1 表示墙
  //          2 表示可以走
  //          3 表示已经走过 但是是死路
  def setWay(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    if (map(6)(5) == 2) { //路已经找到了
      true
    } else {
      if (map(i)(j) == 0) { //可以走还没有走
        //开始递归回溯
        map(i)(j) = 2 //假设该点可以走通
        if (setWay(map, i + 1, j)) { //向下走
          true
        } else if (setWay(map, i, j + 1)) { //向右走
          true
        } else if (setWay(map, i - 1, j)) { //向上走
          true
        } else if (setWay(map, i, j - 1)) { //向左走
          true
        } else {
          //走不通
          map(i)(j) = 3
          false
        }
      } else { //map(i)(j) != 0
        false
      }
    }
  }


}
