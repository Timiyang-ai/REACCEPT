  @Test
  public void processElement() throws Exception {
    for (DoFnTester.CloningBehavior cloning : DoFnTester.CloningBehavior.values()) {
      try (DoFnTester<Long, String> tester = DoFnTester.of(new CounterDoFn())) {
        tester.setCloningBehavior(cloning);
        tester.processElement(1L);

        List<String> take = tester.takeOutputElements();

        assertThat(take, hasItems("1"));

        // Following takeOutputElements(), neither takeOutputElements()
        // nor peekOutputElements() return anything.
        assertTrue(tester.takeOutputElements().isEmpty());
        assertTrue(tester.peekOutputElements().isEmpty());
      }
    }
  }