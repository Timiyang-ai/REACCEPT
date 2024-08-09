public static <T, R> Result<R> flatCombine(
      Iterable<? extends Result<T>> results,
      Function<Stream<T>, Result<R>> function) {

    return allSuccessful(results) ?
        function.apply(extractSuccesses(results)) :
        failure(results);
  }