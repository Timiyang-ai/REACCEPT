  @ParameterizedTest
  @MethodSource("data_events")
  public void test_eventsPerYear(Frequency test, int expected) {
    assertThat(test.eventsPerYear()).isEqualTo(expected);
  }