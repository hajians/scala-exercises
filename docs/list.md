# Exercises

1. Add a map method to the generic type class of `List`. More precisely if
   `List[+A]` then `map[B]` takes `A => B` and returns a `List[B]`.
2. Add a filter method to the generic type class of `List[A]` such that it takes a function `A => Boolean` and returns a
   sub-list such that for every element in the sub-list `f(x)` is `true`.