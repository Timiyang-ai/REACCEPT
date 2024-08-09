public static <T, R> Result<R> combine(
      Iterable<? extends Result<T>> results,
      Function<Stream<T>, R> function) {

    return allSuccessful(results) ?
        success(function.apply(extractSuccesses(results))) :
        failure(results);
  }