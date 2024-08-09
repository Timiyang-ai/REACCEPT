@Test
  public void testOnElement() throws Exception {
    setUp(FixedWindows.of(Duration.millis(10)));
    tester.injectElements(37);
    verify(mockTrigger).onElement(Mockito.<Trigger<IntervalWindow>.OnElementContext>any());
  }