@Override
  public ImmutableList<RateAccrualPeriod> expand(Schedule accrualSchedule, Schedule paymentSchedule) {
    ArgChecker.notNull(accrualSchedule, "accrualSchedule");
    ArgChecker.notNull(paymentSchedule, "paymentSchedule");
    // avoid null stub definitions if there are stubs
    Optional<SchedulePeriod> scheduleInitialStub = accrualSchedule.getInitialStub();
    Optional<SchedulePeriod> scheduleFinalStub = accrualSchedule.getFinalStub();
    if ((scheduleInitialStub.isPresent() && initialStub == null) ||
        (scheduleFinalStub.isPresent() && finalStub == null)) {
      return toBuilder()
          .initialStub(firstNonNull(initialStub, StubCalculation.NONE))
          .finalStub(firstNonNull(finalStub, StubCalculation.NONE))
          .build()
          .expand(accrualSchedule, paymentSchedule);
    }
    // resolve data by schedule
    List<Double> resolvedGearings = firstNonNull(gearing, VALUE_SCHEDULE_1).resolveValues(accrualSchedule.getPeriods());
    List<Double> resolvedSpreads = firstNonNull(spread, VALUE_SCHEDULE_0).resolveValues(accrualSchedule.getPeriods());
    // build accrual periods
    ImmutableList.Builder<RateAccrualPeriod> accrualPeriods = ImmutableList.builder();
    for (int i = 0; i < accrualSchedule.size(); i++) {
      SchedulePeriod period = accrualSchedule.getPeriod(i);
      accrualPeriods.add(RateAccrualPeriod.builder(period)
          .yearFraction(period.yearFraction(dayCount, accrualSchedule))
          .rateObservation(createRateObservation(period, accrualSchedule.getRollConvention(), i, scheduleInitialStub, scheduleFinalStub))
          .negativeRateMethod(negativeRateMethod)
          .gearing(resolvedGearings.get(i))
          .spread(resolvedSpreads.get(i))
          .build());
    }
    return accrualPeriods.build();
  }