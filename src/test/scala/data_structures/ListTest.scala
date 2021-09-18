package data_structures

import org.scalatest.FunSuite

class ListTest extends FunSuite {

  test("testCovariant") {
    // Given
    val l: List[Int] = new List[Int](h = 1, new List[Int](h = 20))

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

  test("testMap") {
    // Given
    val l: List[Int] = new List[Int](h = 1).append(20).append(30).append(-1)
    val square: Int => Int = (x: Int) => x * x

    // When
    val lMapped = l.map(square)

    // Then
    assert(lMapped.head == 1)
    assert(lMapped.tail.head == 400)
    assert(lMapped.tail.tail.head == 900)
    assert(lMapped.tail.tail.tail.head == 1)
  }

  test("testMapDifferentCoDomain") {
    // Given
    val l: List[String] = new List[String](h = "1").append("2").append("3").append("4")
    val square: String => Int = (x: String) => x.toInt

    // When
    val lMapped = l.map(square)

    // Then
    assert(lMapped.head == 1)
    assert(lMapped.tail.head == 2)
    assert(lMapped.tail.tail.head == 3)
    assert(lMapped.tail.tail.tail.head == 4)
    assert(lMapped.getLength == 4)
  }

  test("testFilter") {
    // Given
    val mod2: Int => Boolean = (x: Int) => if ((x%2)==0) true else false
    val l: List[Int] = new List[Int](2).append(3).append(4).append(5)

    // When
    val lFiltered: List[Int] = l.filter(mod2)

    // Then
    assert(lFiltered.head == 2)
    assert(lFiltered.tail.head == 4)
    assert(lFiltered.getLength == 2)
  }
}
