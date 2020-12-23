package com.datastructures.recursive

import scala.annotation.tailrec

object RecursiveDemo {
  def main(args: Array[String]): Unit = {
    test(4)
  }

  @tailrec
  def test(n: Int): Unit = {
    if (n > 2) {
      test(n - 1)
    }else{
      println("n=" + n)
    }
  }
}
