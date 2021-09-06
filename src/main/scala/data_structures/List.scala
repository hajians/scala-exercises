package data_structures

class List[+A](h: A, t: AbstractStack[A] = EmptyStack) extends Stack[A](h = h, t = t) {

  override def push[B >: A](n: B): List[B] = new List[B](n, this)

  def invert: List[A] = {
    def trInvert(l: AbstractStack[A], accumulator: List[A]): List[A] = {
      if (l == EmptyStack) accumulator
      else trInvert(l.tail, accumulator.push(l.head))
    }

    trInvert(this.tail, new List[A](this.head))
  }
}