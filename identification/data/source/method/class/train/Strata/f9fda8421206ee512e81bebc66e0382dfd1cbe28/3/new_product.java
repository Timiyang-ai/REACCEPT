@Override
  public ImmutableList<RateAccrualPeriod> expand(Schedule accrualSchedule, Schedule paymentSchedule) {
    ArgChecker.notNull(accrualSchedule, "accrualSchedule");
    ArgChecker.notNull(paymentSchedule, "paymentSchedule");
    // resolve data by schedule
    List<Double> resolvedGearings = firstNonNull(gearing, ALWAYS_1).resolveValues(accrualSchedule.getPeriods());
    List<Double> resolvedSpreads = firstNonNull(spread, ALWAYS_0).resolveValues(accrualSchedule.getPeriods());
    // build accrual periods
    ImmutableList.Builder<RateAccrualPeriod> accrualPeriods = ImmutableList.builder();
    for (int i = 0; i < accrualSchedule.size(); i++) {
      SchedulePeriod period = accrualSchedule.getPeriod(i);
      accrualPeriods.add(RateAccrualPeriod.builder(period)
          .yearFraction(period.yearFraction(dayCount, accrualSchedule))
          .rateObservation(createRateObservation(period, paymentSchedule))
          .negativeRateMethod(negativeRateMethod)
          .gearing(resolvedGearings.get(i))
          .spread(resolvedSpreads.get(i))
          .build());
    }
    return accrualPeriods.build();
  }