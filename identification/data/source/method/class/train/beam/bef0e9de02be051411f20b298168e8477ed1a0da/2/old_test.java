  @Test
  public void processTimestampedElement() throws Exception {
    try (DoFnTester<Long, TimestampedValue<Long>> tester = DoFnTester.of(new ReifyTimestamps())) {
      TimestampedValue<Long> input = TimestampedValue.of(1L, new Instant(100));
      tester.processTimestampedElement(input);
      assertThat(tester.takeOutputElements(), contains(input));
    }
  }