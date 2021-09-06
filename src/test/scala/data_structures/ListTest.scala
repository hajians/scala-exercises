package data_structures

import org.scalatest.FunSuite

class ListTest extends FunSuite {

  test("testCovariant") {
    // Given
    val l: List[Int] = new List[Int](h=1, new List[Int](h=20))

    // When, Then
    val lCovariant: List[Any] = new List[Any]("String", l)
  }

  test("testInvert") {
    // Given
    val l: List[Int] = new List[Int](h = 1).push(20)

    // When
    val lInverted: List[Int] = l.invert

    // Then
    assert(lInverted.head === 1)
    assert(lInverted.tail.head === 20)
  }

  test("testAppend") {
    // Given
    val l: List[Int] = new List[Int](h = 1, new List[Int](20))

    // When
    val lAppended: List[Int] = l.append(30)

    // Then
    assert(lAppended.head === 1)
    assert(lAppended.tail.head === 20)
    assert(lAppended.tail.tail.head === 30)
  }
}
