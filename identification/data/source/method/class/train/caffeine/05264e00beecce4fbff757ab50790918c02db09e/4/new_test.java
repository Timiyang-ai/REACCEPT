  @Test
  public void expire_reschedule() {
    when(cache.evictEntry(captor.capture(), any(), anyLong())).thenAnswer(invocation -> {
      Timer timer = (Timer) invocation.getArgument(0);
      timer.setVariableTime(timerWheel.nanos + 100);
      return false;
    });

    timerWheel.nanos = NOW;
    timerWheel.schedule(new Timer(NOW + 100));
    timerWheel.advance(NOW + TimerWheel.SPANS[0]);

    verify(cache).evictEntry(any(), any(), anyLong());
    assertThat(captor.getValue().getNextInVariableOrder(), is(not(nullValue())));
    assertThat(captor.getValue().getPreviousInVariableOrder(), is(not(nullValue())));
  }