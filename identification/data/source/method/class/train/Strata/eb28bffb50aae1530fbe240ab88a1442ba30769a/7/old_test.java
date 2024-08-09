  @Test
  public void test_consumer_success() {
    Consumer<String> a = Unchecked.consumer((t) -> {});
    a.accept("A");
  }