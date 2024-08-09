  @Test
  public void deschedule() {
    Timer timer = new Timer(NOW + 100);
    timerWheel.nanos = NOW;
    timerWheel.schedule(timer);
    timerWheel.deschedule(timer);
    assertThat(timer.getNextInVariableOrder(), is(nullValue()));
    assertThat(timer.getPreviousInVariableOrder(), is(nullValue()));
  }