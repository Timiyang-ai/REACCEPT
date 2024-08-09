  @Test
  public void processBundle() throws Exception {
    for (DoFnTester.CloningBehavior cloning : DoFnTester.CloningBehavior.values()) {
      try (DoFnTester<Long, String> tester = DoFnTester.of(new CounterDoFn())) {
        tester.setCloningBehavior(cloning);
        // processBundle() returns all the output like takeOutputElements().
        assertThat(tester.processBundle(1L, 2L, 3L, 4L), hasItems("1", "2", "3", "4"));

        // peek now returns nothing.
        assertTrue(tester.peekOutputElements().isEmpty());
      }
    }
  }