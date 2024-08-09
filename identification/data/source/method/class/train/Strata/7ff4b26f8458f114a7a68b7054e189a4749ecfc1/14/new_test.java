  @Test
  public void test_combineWith_same() {
    HolidayCalendar base = new MockHolCal();
    HolidayCalendar test = base.combinedWith(base);
    assertThat(test).isSameAs(base);
  }