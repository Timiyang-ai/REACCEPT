  @Test
  public void addSecondsToEpochTimeTest() {
    for (int i = 0; i < 5; i++) {
      long epochTimeInMs = SystemTime.getInstance().milliseconds() + TestUtils.RANDOM.nextInt(10000);
      long deltaInSecs = TestUtils.RANDOM.nextInt(10000);
      assertEquals("epoch epochTimeInMs mismatch ", epochTimeInMs + (deltaInSecs * Time.MsPerSec),
          Utils.addSecondsToEpochTime(epochTimeInMs, deltaInSecs));
      assertEquals("epoch epochTimeInMs mismatch ", Utils.Infinite_Time,
          Utils.addSecondsToEpochTime(Utils.Infinite_Time, deltaInSecs));
      assertEquals("epoch epochTimeInMs mismatch ", Utils.Infinite_Time,
          Utils.addSecondsToEpochTime(epochTimeInMs, Utils.Infinite_Time));
    }
  }