@Test
  public void testShouldFire() throws Exception {
    setUp(FixedWindows.of(Duration.millis(10)));

    when(mockTrigger.shouldFire(anyTriggerContext())).thenReturn(true);
    assertTrue(tester.shouldFire(new IntervalWindow(new Instant(0), new Instant(10))));

    when(mockTrigger.shouldFire(Mockito.<Trigger.TriggerContext>any()))
        .thenReturn(false);
    assertFalse(tester.shouldFire(new IntervalWindow(new Instant(0), new Instant(10))));
  }