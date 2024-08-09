  @Test
  public void reschedule() {
    when(cache.evictEntry(captor.capture(), any(), anyLong())).thenReturn(true);
    timerWheel.nanos = NOW;

    Timer timer = new Timer(NOW + TimeUnit.MINUTES.toNanos(15));
    timerWheel.schedule(timer);
    Node<?, ?> startBucket = timer.getNextInVariableOrder();

    timer.setVariableTime(NOW + TimeUnit.HOURS.toNanos(2));
    timerWheel.reschedule(timer);
    assertThat(timer.getNextInVariableOrder(), is(not(startBucket)));

    timerWheel.advance(NOW + TimeUnit.DAYS.toNanos(1));
    checkEmpty();
  }