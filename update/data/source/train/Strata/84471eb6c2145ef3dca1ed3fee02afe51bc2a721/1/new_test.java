@Test(dataProvider = "generation")
  public void coverage_equals(
      LocalDate start, LocalDate end, Frequency freq, StubConvention stubConv, RollConvention rollConv,
      LocalDate firstReg, LocalDate lastReg, List<LocalDate> unadjusted, List<LocalDate> adjusted) {
    PeriodicScheduleDefn a1 = of(start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn a2 = of(start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn b = of(LocalDate.MIN, end, freq, BDA, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn c = of(start, LocalDate.MAX, freq, BDA, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn d = of(
        start, end, freq == P1M ? P3M : P1M, BDA, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn e = of(
        start, end, freq, BusinessDayAdjustment.NONE, stubConv, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn f = of(
        start, end, freq, BDA, stubConv == STUB_NONE ? SHORT_FINAL : STUB_NONE, rollConv, firstReg, lastReg, null, null);
    PeriodicScheduleDefn g = of(start, end, freq, BDA, stubConv, SFE, firstReg, lastReg, null, null);
    PeriodicScheduleDefn h = of(start, end, freq, BDA, stubConv, rollConv, start.plusDays(1), lastReg, null, null);
    PeriodicScheduleDefn i = of(start, end, freq, BDA, stubConv, rollConv, firstReg, end.minusDays(1), null, null);
    PeriodicScheduleDefn j = of(start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg, BDA, null);
    PeriodicScheduleDefn k = of(start, end, freq, BDA, stubConv, rollConv, firstReg, lastReg, null, BDA);
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
    assertEquals(a1.equals(j), false);
    assertEquals(a1.equals(k), false);
  }