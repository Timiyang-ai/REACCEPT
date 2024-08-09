  @Test
  public void test_resolve() {
    TermDeposit base = TermDeposit.builder()
        .buySell(SELL)
        .startDate(START_DATE)
        .endDate(END_DATE)
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .dayCount(ACT_365F)
        .notional(NOTIONAL)
        .currency(GBP)
        .rate(RATE)
        .build();
    ResolvedTermDeposit test = base.resolve(REF_DATA);
    LocalDate expectedEndDate = BDA_MOD_FOLLOW.adjust(END_DATE, REF_DATA);
    double expectedYearFraction = ACT_365F.yearFraction(START_DATE, expectedEndDate);
    assertThat(test.getStartDate()).isEqualTo(START_DATE);
    assertThat(test.getEndDate()).isEqualTo(expectedEndDate);
    assertThat(test.getNotional()).isEqualTo(-NOTIONAL);
    assertThat(test.getYearFraction()).isCloseTo(expectedYearFraction, offset(EPS));
    assertThat(test.getInterest()).isCloseTo(-RATE * expectedYearFraction * NOTIONAL, offset(NOTIONAL * EPS));
    assertThat(test.getRate()).isEqualTo(RATE);
    assertThat(test.getCurrency()).isEqualTo(GBP);
  }