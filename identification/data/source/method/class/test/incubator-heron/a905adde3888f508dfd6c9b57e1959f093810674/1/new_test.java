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
    Duration interval = Duration.ofSeconds(1);
    slaveLooper.registerTimerEvent(interval, r);
    slaveLooper.loop();
    long endTime = System.nanoTime();
    Assert.assertTrue(endTime - startTime - interval.toNanos() >= 0);
    Assert.assertEquals(10, globalValue);
  }