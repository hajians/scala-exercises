package data_structures

import scala.annotation.tailrec

abstract class AbstractStack[+A] {
  def head: A

  def tail: AbstractStack[A]

  def push[B >: A](n: B): AbstractStack[B]
}

object EmptyStack extends AbstractStack[Nothing] {
  def head: Nothing = ???

  def tail: Nothing = ???

  def push[B >: Nothing](n: B): AbstractStack[B] = Stack[B](h=n)
}

class Stack[+A](h: A, t: AbstractStack[A]) extends AbstractStack[A] {
  def head: A = h

  def tail: AbstractStack[A] = t

  def push[B >: A](n: B): Stack[B] = new Stack[B](h = n, t = this)

  def getLength: Int = {
    @tailrec
    def trLength[B >: A](l: AbstractStack[B], accumulator: Int): Int = {
      if (l == EmptyStack) accumulator
      else trLength(l.tail, accumulator + 1)
    }

    trLength(this, 0)
  }

  def pop: (A, AbstractStack[A]) = {
    (head, tail)
  }

}

object Stack {
  def apply[A](h: A): Stack[A] = new Stack[A](h, EmptyStack)

}

