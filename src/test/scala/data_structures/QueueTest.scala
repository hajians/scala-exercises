package data_structures

import org.scalatest.FunSuite

class QueueTest extends FunSuite {

  test("testPush") {
    // Given
    val q = new Queue[Int](1, EmptyQueue)

    // When
    val qAdded = q.push(2)

    // Then
    assert(qAdded.head === 2)
  }

  test("testGetLength") {
    // Given
    val q = new Queue[Int](1, EmptyQueue).push(2).push(3)

    // When
    val length: Int = q.getLength

    // Then
    assert(length === 3)
  }

}
