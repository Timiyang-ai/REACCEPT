public static <T, R> Result<R> flatCombine(
      Iterable<? extends Result<T>> results,
      Function<Stream<T>, Result<R>> function) {

    try {
      return allSuccessful(results) ?
          function.apply(extractSuccesses(results)) :
          failure(results);

    } catch (Exception e) {
      return failure(e, "Error whilst combining success results");
    }
  }