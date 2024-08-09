  @ParameterizedTest
  @MethodSource("data_factory")
  public void test_ofPair(double first, double second) {
    Pair<Double, Double> pair = Pair.of(first, second);
    DoublesPair test = DoublesPair.ofPair(pair);
    assertThat(test.getFirst()).isEqualTo(first, TOLERANCE);
    assertThat(test.getSecond()).isEqualTo(second, TOLERANCE);
  }