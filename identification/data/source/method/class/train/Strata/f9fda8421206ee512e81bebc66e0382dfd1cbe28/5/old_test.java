  @Test
  public void test_expand_simple() {
    IborRateCalculation test = IborRateCalculation.builder()
        .dayCount(ACT_365F)
        .index(GBP_LIBOR_1M)
        .fixingDateOffset(MINUS_TWO_DAYS)
        .build();
    RateAccrualPeriod rap1 = RateAccrualPeriod.builder(ACCRUAL1)
        .yearFraction(ACCRUAL1.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(IborRateComputation.of(GBP_LIBOR_1M, DATE_01_02, REF_DATA))
        .build();
    RateAccrualPeriod rap2 = RateAccrualPeriod.builder(ACCRUAL2)
        .yearFraction(ACCRUAL2.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(IborRateComputation.of(GBP_LIBOR_1M, DATE_02_03, REF_DATA))
        .build();
    RateAccrualPeriod rap3 = RateAccrualPeriod.builder(ACCRUAL3)
        .yearFraction(ACCRUAL3.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(IborRateComputation.of(GBP_LIBOR_1M, DATE_03_03, REF_DATA))
        .build();
    ImmutableList<RateAccrualPeriod> periods = test.createAccrualPeriods(ACCRUAL_SCHEDULE, ACCRUAL_SCHEDULE, REF_DATA);
    assertThat(periods).containsExactly(rap1, rap2, rap3);
  }