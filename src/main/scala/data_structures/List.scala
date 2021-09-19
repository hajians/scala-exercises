package data_structures

import scala.annotation.tailrec

class List[+A](h: A, t: AbstractStack[A] = EmptyStack) extends Stack[A](h = h, t = t) {

  override def push[B >: A](n: B): List[B] = new List[B](n, this)

  def append[B >: A](n: B): List[B] = this.invert.push(n).invert

  def invert: List[A] = {
    @tailrec
    def trInvert(l: AbstractStack[A], accumulator: List[A]): List[A] = {
      if (l == EmptyStack) accumulator
      else trInvert(l.tail, accumulator.push(l.head))
    }

    trInvert(this.tail, new List[A](this.head))
  }

  def map[B](f: A => B): List[B] = {
    @tailrec
    def trMap(l: AbstractStack[A], accumulator: List[B]): List[B] = {
      if (l == EmptyStack) accumulator
      else trMap(l.tail, accumulator.push(f(l.head)))
    }

    trMap(this.tail, new List[B](f(this.head))).invert
  }

  def filter(f: A => Boolean): List[A] = {
    @tailrec
    def trFilter(f: A => Boolean, l: AbstractStack[A], accumulator: AbstractStack[A]): AbstractStack[A] = {
      if (l == EmptyStack) accumulator
      else if (f(l.head)) trFilter(f, l.tail, accumulator.push(l.head))
      else trFilter(f, l.tail, accumulator)
    }

    List.convertStackToList(trFilter(f, this, EmptyStack)).invert
  }

  def slice(start: Int, end: Int): List[A] = {
    @tailrec
    def trSlice(index: Int, start: Int, end: Int, l: AbstractStack[A], accumulator: AbstractStack[A]): AbstractStack[A] = {
      if (start >= end) EmptyStack
      else if (l == EmptyStack) accumulator
      else if (index >= end) accumulator
      else if (index < start) trSlice(index + 1, start, end, l.tail, accumulator)
      else trSlice(index + 1, start, end, l.tail, accumulator.push(l.head))
    }

    val stack: AbstractStack[A] = trSlice(0, start, end, this, EmptyStack)
    if (stack == EmptyStack) throw new RuntimeException("Could not slice the list")
    else List.convertStackToList(stack).invert
  }

  def getElement(pos: Int): A = {
    if (pos >= 0) slice(start = pos, end = pos + 1).head
    else slice(start = this.getLength + pos, end = this.getLength + pos + 1).head
  }
}

object List {
  def convertStackToList[A](s: AbstractStack[A]): List[A] = {
    @tailrec
    def trConvert(s: AbstractStack[A], accumulator: List[A]): List[A] = {
      if (s == EmptyStack) accumulator
      else trConvert(s.tail, accumulator.push(s.head))
    }

    trConvert(s.tail, new List[A](s.head)).invert
  }

  def aRange(start: Int, stop: Int, step: Int = 1): List[Int] = {
    @tailrec
    def trRange(i: Int, stop: Int, step: Int, accumulator: List[Int]): List[Int] = {
      if (i >= stop) accumulator
      else trRange(i + step, stop, step, accumulator.push(i))
    }

    trRange(i = start + step, stop = stop, step = step, new List[Int](start)).invert
  }
}
