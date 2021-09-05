package data_structures

import org.scalatest.FunSuite

class StackTest extends FunSuite {

  test("testPush") {
    // Given
    val q = new Stack[Int](1, EmptyStack)

    // When
    val qAdded = q.push(2)

    // Then
    assert(qAdded.head === 2)
  }

  test("testGetLength") {
    // Given
    val q = new Stack[Int](1, EmptyStack).push(2).push(3)

    // When
    val length: Int = q.getLength

    // Then
    assert(length === 3)
  }

  test("testPop") {
    // Given
    val q = new Stack[Int](1, EmptyStack).push(2).push(3)

    // When
    val (value, qNew) = q.pop

    // Then
    assert(value === 3)
    assert(qNew === q.tail)
  }

}
