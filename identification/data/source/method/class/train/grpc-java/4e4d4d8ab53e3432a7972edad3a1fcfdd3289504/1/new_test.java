  @Test
  public void compareTo_greater() {
    Deadline d1 = Deadline.after(10, TimeUnit.SECONDS, ticker);
    ticker.increment(1, TimeUnit.NANOSECONDS);
    Deadline d2 = Deadline.after(10, TimeUnit.SECONDS, ticker);
    Truth.assertThat(d2).isGreaterThan(d1);
  }