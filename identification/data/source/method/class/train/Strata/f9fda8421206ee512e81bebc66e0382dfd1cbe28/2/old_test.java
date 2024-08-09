  @Test
  public void test_expand_oneValue() {
    FixedRateCalculation test = FixedRateCalculation.builder()
        .dayCount(ACT_365F)
        .rate(ValueSchedule.of(0.025d))
        .build();
    SchedulePeriod period1 = SchedulePeriod.of(date(2014, 1, 6), date(2014, 2, 5), date(2014, 1, 5), date(2014, 2, 5));
    SchedulePeriod period2 = SchedulePeriod.of(date(2014, 1, 5), date(2014, 2, 5), date(2014, 2, 5), date(2014, 3, 5));
    SchedulePeriod period3 = SchedulePeriod.of(date(2014, 3, 5), date(2014, 4, 7), date(2014, 3, 5), date(2014, 4, 5));
    Schedule schedule = Schedule.builder()
        .periods(period1, period2, period3)
        .frequency(Frequency.P1M)
        .rollConvention(RollConventions.DAY_5)
        .build();
    RateAccrualPeriod rap1 = RateAccrualPeriod.builder(period1)
        .yearFraction(period1.yearFraction(ACT_365F, schedule))
        .rateComputation(FixedRateComputation.of(0.025d))
        .build();
    RateAccrualPeriod rap2 = RateAccrualPeriod.builder(period2)
        .yearFraction(period2.yearFraction(ACT_365F, schedule))
        .rateComputation(FixedRateComputation.of(0.025d))
        .build();
    RateAccrualPeriod rap3 = RateAccrualPeriod.builder(period3)
        .yearFraction(period3.yearFraction(ACT_365F, schedule))
        .rateComputation(FixedRateComputation.of(0.025d))
        .build();
    ImmutableList<RateAccrualPeriod> periods = test.createAccrualPeriods(schedule, schedule, REF_DATA);
    assertThat(periods).containsExactly(rap1, rap2, rap3);
  }