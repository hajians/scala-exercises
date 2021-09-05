package data_structures

import scala.annotation.tailrec

abstract class AbstractQueue[+A] {
  def head: A

  def tail: AbstractQueue[A]

  def push[B >: A](n: B): AbstractQueue[B]
}

object EmptyQueue extends AbstractQueue[Nothing] {
  def head: Nothing = ???

  def tail: Nothing = ???

  def push[B >: Nothing](n: B): AbstractQueue[B] = ???
}

class Queue[+A](h: A, t: AbstractQueue[A]) extends AbstractQueue[A] {
  def head: A = h

  def tail: AbstractQueue[A] = t

  def push[B >: A](n: B): Queue[B] = new Queue[B](h = n, t = this)

  def getLength: Int = {
    @tailrec
    def rtLength[B>:A](l: AbstractQueue[B], accumulator: Int): Int = {
      if (l==EmptyQueue) accumulator
      else rtLength(l.tail, accumulator+1)
    }
    rtLength(this, 0)
  }
}

object Queue {
  def apply[A](h: A): Queue[A] = new Queue[A](h, EmptyQueue)

}

