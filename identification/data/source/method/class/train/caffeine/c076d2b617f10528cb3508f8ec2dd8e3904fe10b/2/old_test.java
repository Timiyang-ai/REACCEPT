  @Test(dataProvider = "schedule")
  public void schedule(long nanos, int expired) {
    when(cache.evictEntry(captor.capture(), any(), anyLong())).thenReturn(true);

    timerWheel.nanos = NOW;
    for (int timeout : new int[] { 25, 90, 240 }) {
      timerWheel.schedule(new Timer(NOW + TimeUnit.SECONDS.toNanos(timeout)));
    }
    timerWheel.advance(NOW + nanos);
    verify(cache, times(expired)).evictEntry(any(), any(), anyLong());

    for (Node<?, ?> node : captor.getAllValues()) {
      assertThat(node.getVariableTime(), is(lessThan(NOW + nanos)));
    }
  }