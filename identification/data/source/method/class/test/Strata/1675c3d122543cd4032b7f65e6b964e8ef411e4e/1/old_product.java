@Override
  public ResolvedCapitalIndexedBond resolve(ReferenceData refData) {
    Schedule schedule = periodicSchedule.createSchedule(refData);
    DateAdjuster exCouponPeriodAdjuster = exCouponPeriod.resolve(refData);
    List<Double> resolvedGearings =
        rateCalculation.getGearing().orElse(ALWAYS_1).resolveValues(schedule.getPeriods());
    ImmutableList.Builder<CapitalIndexedBondPaymentPeriod> bondPeriodsBuilder = ImmutableList.builder();
    // coupon payments
    for (int i = 0; i < schedule.size(); i++) {
      SchedulePeriod period = schedule.getPeriod(i);
      bondPeriodsBuilder.add(CapitalIndexedBondPaymentPeriod.builder()
          .unadjustedStartDate(period.getUnadjustedStartDate())
          .unadjustedEndDate(period.getUnadjustedEndDate())
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .detachmentDate(exCouponPeriodAdjuster.adjust(period.getEndDate()))
          .notional(notional)
          .currency(currency)
          .rateObservation(rateCalculation.createRateObservation(period.getEndDate(), startIndexValue))
          .realCoupon(resolvedGearings.get(i))
          .build());
    }
    ImmutableList<CapitalIndexedBondPaymentPeriod> bondPeriods = bondPeriodsBuilder.build();
    // nominal payment
    CapitalIndexedBondPaymentPeriod nominalPayment = bondPeriods.get(bondPeriods.size() - 1)
        .withUnitCoupon(bondPeriods.get(0).getStartDate(), bondPeriods.get(0).getUnadjustedStartDate());
    return ResolvedCapitalIndexedBond.builder()
        .periodicPayments(ImmutableList.copyOf(bondPeriods))
        //        .frequency(periodicSchedule.getFrequency())
        //        .rollConvention(periodicSchedule.calculatedRollConvention())
        .dayCount(dayCount)
        .yieldConvention(yieldConvention)
        .settlementDateOffset(settlementDateOffset)
        .legalEntityId(legalEntityId)
        .nominalPayment(nominalPayment)
        .notional(notional)
        .periodicSchedule(periodicSchedule)
        .rateCalculation(rateCalculation)
        .startIndexValue(startIndexValue)
        .build();
  }