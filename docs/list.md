# Exercises

1. Add a map method to the generic type class of `List`. More precisly if
   `List[+A]` then `map[B >: A]` takes `B => B` and returns a `List[B]`.