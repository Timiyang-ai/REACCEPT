@Override
  public ResolvedFixedCouponBond resolve(ReferenceData refData) {
    Schedule adjustedSchedule = periodicSchedule.createSchedule(refData);
    Schedule unadjustedSchedule = adjustedSchedule.toUnadjusted();
    DateAdjuster exCouponPeriodAdjuster = exCouponPeriod.resolve(refData);

    ImmutableList.Builder<FixedCouponBondPaymentPeriod> accrualPeriods = ImmutableList.builder();
    for (int i = 0; i < adjustedSchedule.size(); i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      SchedulePeriod unadjustedPeriod = unadjustedSchedule.getPeriod(i);
      accrualPeriods.add(FixedCouponBondPaymentPeriod.builder()
          .unadjustedStartDate(period.getUnadjustedStartDate())
          .unadjustedEndDate(period.getUnadjustedEndDate())
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .detachmentDate(exCouponPeriodAdjuster.adjust(period.getEndDate()))
          .notional(notional)
          .currency(currency)
          .fixedRate(fixedRate)
          .yearFraction(unadjustedPeriod.yearFraction(dayCount, unadjustedSchedule))
          .build());
    }
    ImmutableList<FixedCouponBondPaymentPeriod> periodicPayments = accrualPeriods.build();
    FixedCouponBondPaymentPeriod lastPeriod = periodicPayments.get(periodicPayments.size() - 1);
    Payment nominalPayment = Payment.of(CurrencyAmount.of(currency, notional), lastPeriod.getPaymentDate());
    return ResolvedFixedCouponBond.builder()
        .legalEntityId(legalEntityId)
        .nominalPayment(nominalPayment)
        .periodicPayments(ImmutableList.copyOf(periodicPayments))
        .frequency(periodicSchedule.getFrequency())
        .rollConvention(periodicSchedule.calculatedRollConvention())
        .fixedRate(fixedRate)
        .dayCount(dayCount)
        .yieldConvention(yieldConvention)
        .settlementDateOffset(settlementDateOffset)
        .build();
  }