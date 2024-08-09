public static <T, R> Result<R> combine(
      Iterable<? extends Result<T>> results,
      Function<Stream<T>, R> function) {

    try {
      return allSuccessful(results) ?
          success(function.apply(extractSuccesses(results))) :
          failure(results);

    } catch (Exception e) {
      return failure(e);
    }
  }