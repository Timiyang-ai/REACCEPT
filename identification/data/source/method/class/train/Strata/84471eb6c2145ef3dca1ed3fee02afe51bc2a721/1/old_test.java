@Test(dataProvider = "generation")
  public void coverage_equals(
      AdjustableDate start, AdjustableDate end, Frequency freq, StubConvention stubConv, RollConvention rollConv,
      LocalDate firstReg, LocalDate lastReg, List<LocalDate> unadjusted, List<LocalDate> adjusted) {
    PeriodicScheduleDefn a1 = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn a2 = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn b = PeriodicScheduleDefn.of(
        ad(LocalDate.MIN), end, freq, BDA, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn c = PeriodicScheduleDefn.of(
        start, ad(LocalDate.MAX), freq, BDA, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn d = PeriodicScheduleDefn.of(
        start, end, freq == P1M ? P3M : P1M, BDA, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn e = PeriodicScheduleDefn.of(
        start, end, freq, BusinessDayAdjustment.NONE, stubConv, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn f = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv == STUB_NONE ? SHORT_FINAL : STUB_NONE, rollConv, firstReg, lastReg);
    PeriodicScheduleDefn g = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv, SFE, firstReg, lastReg);
    PeriodicScheduleDefn h = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv, rollConv, start.getUnadjusted().plusDays(1), lastReg);
    PeriodicScheduleDefn i = PeriodicScheduleDefn.of(
        start, end, freq, BDA, stubConv, rollConv, firstReg, end.getUnadjusted().minusDays(1));
    assertEquals(a1.equals(a1), true);
    assertEquals(a1.equals(a2), true);
    assertEquals(a1.equals(b), false);
    assertEquals(a1.equals(c), false);
    assertEquals(a1.equals(d), false);
    assertEquals(a1.equals(e), false);
    assertEquals(a1.equals(f), false);
    assertEquals(a1.equals(g), false);
    assertEquals(a1.equals(h), false);
    assertEquals(a1.equals(i), false);
  }