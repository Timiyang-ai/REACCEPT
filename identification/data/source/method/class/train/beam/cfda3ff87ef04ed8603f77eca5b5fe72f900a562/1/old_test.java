@Test
  public void testOnElement() throws Exception {
    setUp(FixedWindows.of(Duration.millis(10)));

    injectElement(1, TriggerResult.CONTINUE);
    assertThat(tester.getLatestResult(), equalTo(TriggerResult.CONTINUE));

    injectElement(2, TriggerResult.FIRE);
    assertThat(tester.getLatestResult(), equalTo(TriggerResult.FIRE));

    injectElement(3, TriggerResult.FIRE_AND_FINISH);
    assertThat(tester.getLatestResult(), equalTo(TriggerResult.FIRE));

    injectElement(4, TriggerResult.CONTINUE);
    assertThat(tester.getLatestResult(), equalTo(TriggerResult.CONTINUE));

    assertFalse(tester.isMarkedFinished(firstWindow));
  }