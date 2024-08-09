  @Test
  public void advanceTime() {
    TestClock clock = TestClock.create(Timestamp.create(1, 500 * 1000 * 1000));
    clock.advanceTime(Duration.create(2, 600 * 1000 * 1000));
    assertThat(clock.now()).isEqualTo(Timestamp.create(4, 100 * 1000 * 1000));
  }