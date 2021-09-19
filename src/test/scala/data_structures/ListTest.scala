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
    val mod2: Int => Boolean = (x: Int) => if ((x % 2) == 0) true else false
    val l: List[Int] = new List[Int](2).append(3).append(4).append(5)

    // When
    val lFiltered: List[Int] = l.filter(mod2)

    // Then
    assert(lFiltered.head == 2)
    assert(lFiltered.tail.head == 4)
    assert(lFiltered.getLength == 2)
  }

  test("testARange") {
    // Given
    val start = 3
    val end = 10
    val step = 2

    // When
    val r: List[Int] = List.aRange(start, end, step)

    // Then
    assert(r.getLength == 4)
    assert(r.head == 3)
    assert(r.tail.head == 5)
    assert(r.tail.tail.head == 7)
    assert(r.tail.tail.tail.head == 9)
  }

  test("testSlice") {
    // Given
    val l = new List[Int](1).append(3).append(5).append(2)

    // When
    val lSlice = l.slice(0, 2)

    // Then
    assert(lSlice.getLength == 2)
    assert(lSlice.head == 1)
    assert(lSlice.tail.head == 3)
  }

  test("testSliceTwo") {
    // Given
    val l = new List[Int](1).append(3).append(5).append(2)

    // When
    val lSlice = l.slice(3, 5)

    // Then
    assert(lSlice.getLength == 1)
    assert(lSlice.head == 2)
  }

  test("testSliceThrowError") {
    // Given
    val l = new List[Int](1).append(3).append(5).append(2)

    // When, Then
    val output = try {
      l.slice(4, 5)
    } catch {
      case e: RuntimeException => 1
    }

    assert(output == 1)
  }

  test("testGetElement") {
    // Given
    val l = new List[Int](1).append(3).append(5).append(2)

    // When, Then
    // Then
    assert(l.getElement(pos = 0) == 1)
    assert(l.getElement(pos = 1) == 3)
    assert(l.getElement(pos = -1) == 2)
  }

}
