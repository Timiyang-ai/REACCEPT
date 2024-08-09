  @Test
  public void test_resolve() {
    IborFixingDeposit base = IborFixingDeposit.builder()
        .buySell(SELL)
        .notional(NOTIONAL)
        .startDate(START_DATE)
        .endDate(END_DATE)
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .index(GBP_LIBOR_6M)
        .fixedRate(RATE)
        .build();
    ResolvedIborFixingDeposit test = base.resolve(REF_DATA);
    LocalDate expectedEndDate = BDA_MOD_FOLLOW.adjust(END_DATE, REF_DATA);
    double expectedYearFraction = ACT_365F.yearFraction(START_DATE, expectedEndDate);
    IborRateComputation expectedObservation = IborRateComputation.of(
        GBP_LIBOR_6M, GBP_LIBOR_6M.getFixingDateOffset().adjust(START_DATE, REF_DATA), REF_DATA);
    assertThat(test.getCurrency()).isEqualTo(GBP);
    assertThat(test.getStartDate()).isEqualTo(START_DATE);
    assertThat(test.getEndDate()).isEqualTo(expectedEndDate);
    assertThat(test.getFloatingRate()).isEqualTo(expectedObservation);
    assertThat(test.getNotional()).isEqualTo(-NOTIONAL);
    assertThat(test.getFixedRate()).isEqualTo(RATE);
    assertThat(test.getYearFraction()).isEqualTo(expectedYearFraction);
  }