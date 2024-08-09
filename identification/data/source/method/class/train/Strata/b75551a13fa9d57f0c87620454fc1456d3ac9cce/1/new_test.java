  @Test
  public void test_value() {
    for (int i = 0; i < TEST_MONTHS.length; i++) {
      double valueComputed = INSTANCE.value(TEST_OBS[i]);
      YearMonth fixingMonth = TEST_OBS[i].getFixingMonth();
      double valueExpected;
      if (USCPI_TS.containsDate(fixingMonth.atEndOfMonth())) {
        valueExpected = USCPI_TS.get(fixingMonth.atEndOfMonth()).getAsDouble();
      } else {
        double x = YearMonth.from(VAL_DATE).until(fixingMonth, MONTHS);
        valueExpected = CURVE_INFL.yValue(x);
      }
      assertThat(valueComputed).as("test " + i).isCloseTo(valueExpected, offset(TOLERANCE_VALUE));
    }
  }