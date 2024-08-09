  @Test
  public void test_resolve_beforeStart_weekend() {
    FxResetCalculation base = FxResetCalculation.builder()
        .index(EUR_GBP_ECB)
        .referenceCurrency(GBP)
        .fixingDateOffset(MINUS_TWO_DAYS)
        .build();
    Optional<FxReset> test = base.resolve(REF_DATA).apply(0, SchedulePeriod.of(DATE_2014_03_31, DATE_2014_06_30));
    assertThat(test).isEqualTo(Optional.of(FxReset.of(FxIndexObservation.of(EUR_GBP_ECB, date(2014, 3, 27), REF_DATA), GBP)));
  }