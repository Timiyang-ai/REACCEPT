@Test
  public void testTransact_getsNewTimestampOnEachTry() {
    ofy().transact(new VoidWork() {

      DateTime firstAttemptTime;

      @Override
      public void vrun() {
        if (firstAttemptTime == null) {
          // Sleep a bit to ensure that the next attempt is at a new millisecond.
          firstAttemptTime = ofy().getTransactionTime();
          sleepUninterruptibly(10, MILLISECONDS);
          throw new ConcurrentModificationException();
        }
        assertThat(ofy().getTransactionTime()).isGreaterThan(firstAttemptTime);
      }});
  }