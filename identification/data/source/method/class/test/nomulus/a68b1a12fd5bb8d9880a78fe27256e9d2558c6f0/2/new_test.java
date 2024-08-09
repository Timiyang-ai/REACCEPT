@Test
  public void testTransact_getsNewTimestampOnEachTry() {
    tm().transact(new Runnable() {

      DateTime firstAttemptTime;

      @Override
      public void run() {
        if (firstAttemptTime == null) {
          // Sleep a bit to ensure that the next attempt is at a new millisecond.
          firstAttemptTime = tm().getTransactionTime();
          sleepUninterruptibly(10, MILLISECONDS);
          throw new ConcurrentModificationException();
        }
        assertThat(tm().getTransactionTime()).isGreaterThan(firstAttemptTime);
      }});
  }