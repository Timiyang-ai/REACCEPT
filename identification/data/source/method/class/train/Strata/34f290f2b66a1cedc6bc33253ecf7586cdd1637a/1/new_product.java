public static <T> CompletableFuture<List<T>> combineFuturesAsList(
      List<? extends CompletableFuture<? extends T>> futures) {

    int size = futures.size();
    CompletableFuture<? extends T>[] futuresArray = futures.toArray(new CompletableFuture[size]);
    return CompletableFuture.allOf(futuresArray)
        .thenApply(unused -> {
          List<T> builder = new ArrayList<>(size);
          for (int i = 0; i < size; i++) {
            builder.add(futuresArray[i].join());
          }
          return builder;
        });
  }