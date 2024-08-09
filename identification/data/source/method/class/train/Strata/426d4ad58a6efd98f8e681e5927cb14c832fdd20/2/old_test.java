  @ParameterizedTest
  @MethodSource("data_factory")
  public void test_ofPair(int first, double second) {
    Pair<Integer, Double> pair = Pair.of(first, second);
    IntDoublePair test = IntDoublePair.ofPair(pair);
    assertThat(test.getFirst()).isEqualTo(first);
    assertThat(test.getSecond()).isEqualTo(second, TOLERANCE);
  }