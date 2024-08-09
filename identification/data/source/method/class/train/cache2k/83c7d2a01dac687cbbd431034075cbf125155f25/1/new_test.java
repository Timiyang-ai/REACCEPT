  @Test
  public void purge() {
    long _START_TIME = 100;
    long _OFFSET = 1;
    int _SIZE = 123;
    SimpleTimer t =
      new SimpleTimer(
        new SimulatedClock(_START_TIME, true),
        SimpleTimerTest.class.getName(), true);
    MyTimerTask[] arr = new MyTimerTask[_SIZE];
    for (int i = 0; i < _SIZE; i++) {
      arr[i] = new MyTimerTask();
      t.schedule(arr[i], _START_TIME + i + _OFFSET);
      if (i%3 == 0) {
        arr[i].cancel();
      }
    }
    t.purge();
    t.timeReachedEvent(_START_TIME + _SIZE + _OFFSET);
    int count = 0;
    for (int i = 0; i < _SIZE; i++) {
      if (arr[i].executed) {
        count++;
      }
    }
    assertEquals(82, count);
  }