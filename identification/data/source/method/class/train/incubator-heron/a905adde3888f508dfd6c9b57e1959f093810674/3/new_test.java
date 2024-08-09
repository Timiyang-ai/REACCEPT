@Test
  public void testRegisterTimerEventInNanoSeconds() throws Exception {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        isTimerEventInvoked = true;
      }
    };
    heronServer.registerTimerEvent(Duration.ZERO, r);

    runBase();

    Assert.assertTrue(isTimerEventInvoked);
  }