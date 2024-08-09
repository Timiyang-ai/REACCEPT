  @Test
  public void test_expand_simple() {
    OvernightRateCalculation test = OvernightRateCalculation.builder()
        .dayCount(ACT_365F)
        .index(GBP_SONIA)
        .build();
    RateAccrualPeriod rap1 = RateAccrualPeriod.builder(ACCRUAL1)
        .yearFraction(ACCRUAL1.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(OvernightCompoundedRateComputation.of(GBP_SONIA, DATE_01_06, DATE_02_05, 0, REF_DATA))
        .build();
    RateAccrualPeriod rap2 = RateAccrualPeriod.builder(ACCRUAL2)
        .yearFraction(ACCRUAL2.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(OvernightCompoundedRateComputation.of(GBP_SONIA, DATE_02_05, DATE_03_05, 0, REF_DATA))
        .build();
    RateAccrualPeriod rap3 = RateAccrualPeriod.builder(ACCRUAL3)
        .yearFraction(ACCRUAL3.yearFraction(ACT_365F, ACCRUAL_SCHEDULE))
        .rateComputation(OvernightCompoundedRateComputation.of(GBP_SONIA, DATE_03_05, DATE_04_07, 0, REF_DATA))
        .build();
    ImmutableList<RateAccrualPeriod> periods = test.createAccrualPeriods(ACCRUAL_SCHEDULE, ACCRUAL_SCHEDULE, REF_DATA);
    assertThat(periods).containsExactly(rap1, rap2, rap3);
  }