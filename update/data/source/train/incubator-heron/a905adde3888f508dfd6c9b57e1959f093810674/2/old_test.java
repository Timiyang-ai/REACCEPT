@Test
  public void testRegisterTimerEventInNanoSeconds() throws Exception {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        isTimerEventInvoked = true;
      }
    };
    heronServer.registerTimerEventInNanoSeconds(0, r);

    runBase();

    Assert.assertTrue(isTimerEventInvoked);
  }