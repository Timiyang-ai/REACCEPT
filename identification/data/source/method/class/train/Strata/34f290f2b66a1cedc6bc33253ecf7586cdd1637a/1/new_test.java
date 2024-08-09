  @Test
  public void test_combineFuturesAsList() {
    CompletableFuture<String> future1 = new CompletableFuture<>();
    future1.complete("A");
    CountDownLatch latch = new CountDownLatch(1);
    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
      try {
        latch.await();
      } catch (InterruptedException ex) {
        // ignore
      }
      return "B";
    });
    List<CompletableFuture<String>> input = ImmutableList.of(future1, future2);

    CompletableFuture<List<String>> test = Guavate.combineFuturesAsList(input);

    assertThat(test.isDone()).isEqualTo(false);
    latch.countDown();
    List<String> combined = test.join();
    assertThat(test.isDone()).isEqualTo(true);
    assertThat(combined.size()).isEqualTo(2);
    assertThat(combined.get(0)).isEqualTo("A");
    assertThat(combined.get(1)).isEqualTo("B");
  }