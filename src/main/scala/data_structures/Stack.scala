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

  def push[B >: Nothing](n: B): AbstractStack[B] = Stack[B](h = n)
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

  def invert: Stack[A] = {
    @tailrec
    def trInvert(l: AbstractStack[A], accumulator: Stack[A]): Stack[A] = {
      if (l == EmptyStack) accumulator
      else trInvert(l.tail, accumulator.push(l.head))
    }

    trInvert(this.tail, new Stack[A](this.head, EmptyStack))
  }

}

object Stack {
  def apply[A](h: A): Stack[A] = new Stack[A](h, EmptyStack)

  @tailrec
  final def insertSorted[B](l: AbstractStack[B], e: B, compare: (B, B) => Boolean, accumulator: AbstractStack[B] = EmptyStack): Stack[B] = {
    val skipFunc = (x: B, y: B) => true

    if ((l == EmptyStack) & (accumulator == EmptyStack)) new Stack(e, EmptyStack)
    else if (l == EmptyStack) {
      val output = if (compare(e, accumulator.head)) accumulator.push(e) else accumulator
      new Stack[B](output.head, output.tail).invert
    }
    else if (compare(e, l.head)) insertSorted(l.tail, e, compare, accumulator.push(l.head))
    else insertSorted(l, e, skipFunc, accumulator.push(e))
  }

  def sort[B](l: AbstractStack[B], compare: (B, B) => Boolean): Stack[B] = {
    def trSort(l: AbstractStack[B], compare: (B, B) => Boolean, sortedStack: AbstractStack[B]): Stack[B] = {
      if (l == EmptyStack) new Stack[B](sortedStack.head, sortedStack.tail)
      else {
        val newSortedStack = Stack.insertSorted(sortedStack, l.head, compare)
        trSort(l.tail, compare, newSortedStack)
      }
    }

    trSort(l.tail, compare, new Stack[B](l.head, EmptyStack))
  }


}

