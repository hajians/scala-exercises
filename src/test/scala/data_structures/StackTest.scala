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

  test("testInvert") {
    // Given
    val l: Stack[Int] = new Stack[Int](h=10, new Stack[Int](h=1, EmptyStack))

    // When
    val lInverted: Stack[Int] = l.invert

    // Then
    assert(lInverted.head === 1)
    assert(lInverted.tail.head === 10)
  }

  test("testInsertSorted") {
    // Given
    val sorted = new Stack[Int](10, EmptyStack).push(8).push(5).push(2).push(1)
    val comparisonFunc: (Int, Int) => Boolean = (a: Int, b: Int) => a > b
    val element: Int = 7

    // When
    val insertedStack = Stack.insertSorted(l=sorted, e=element, compare = comparisonFunc)

    // Then
    assert(insertedStack.head == 1)
    assert(insertedStack.tail.head == 2)
    assert(insertedStack.tail.tail.head == 5)
    assert(insertedStack.tail.tail.tail.head == 7)
    assert(insertedStack.tail.tail.tail.tail.head == 8)
    assert(insertedStack.tail.tail.tail.tail.tail.head == 10)
  }
  test("testInsertSortedSecond") {
    // Given
    val sorted = new Stack[Int](10, EmptyStack).push(8).push(5).push(2).push(1)
    val comparisonFunc: (Int, Int) => Boolean = (a: Int, b: Int) => a > b
    val element: Int = 8

    // When
    val insertedStack = Stack.insertSorted(l=sorted, e=element, compare = comparisonFunc)

    // Then
    assert(insertedStack.head == 1)
    assert(insertedStack.tail.head == 2)
    assert(insertedStack.tail.tail.head == 5)
    assert(insertedStack.tail.tail.tail.head == 8)
    assert(insertedStack.tail.tail.tail.tail.head == 8)
    assert(insertedStack.tail.tail.tail.tail.tail.head == 10)
  }

  test("testSort") {
    // Given
    val q = new Stack[Int](1, EmptyStack).push(4).push(10).push(3)
    val comparisonFunc: (Int, Int) => Boolean = (a: Int, b: Int) => a > b

    // When
    val sortedQ = Stack.sort(q, comparisonFunc)

    // Then
    assert(sortedQ.head == 1)
    assert(sortedQ.tail.head == 3)
    assert(sortedQ.tail.tail.head == 4)
    assert(sortedQ.tail.tail.tail.head == 10)
  }
}
