  @ParameterizedTest
  @MethodSource("data_factory")
  public void test_ofPair(long first, double second) {
    Pair<Long, Double> pair = Pair.of(first, second);
    LongDoublePair test = LongDoublePair.ofPair(pair);
    assertThat(test.getFirst()).isEqualTo(first);
    assertThat(test.getSecond()).isEqualTo(second, TOLERANCE);
  }