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
}
