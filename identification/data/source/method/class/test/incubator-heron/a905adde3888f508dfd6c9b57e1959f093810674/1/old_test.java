@Test
  public void testRegisterTimerEventInSeconds() throws Exception {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        slaveLooper.exitLoop();
        globalValue = 10;
      }
    };

    long startTime = System.nanoTime();
    int intervalSeconds = 1;
    slaveLooper.registerTimerEventInSeconds(intervalSeconds, r);
    slaveLooper.loop();
    long endTime = System.nanoTime();
    Assert.assertTrue(endTime - startTime - (long) intervalSeconds * SECONDS_TO_NANOSECONDS >= 0);
    Assert.assertEquals(10, globalValue);
  }