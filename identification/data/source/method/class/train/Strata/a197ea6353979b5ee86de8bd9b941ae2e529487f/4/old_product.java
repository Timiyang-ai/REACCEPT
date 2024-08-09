@Override
  public ResolvedFixedCouponBond resolve(ReferenceData refData) {
    Schedule adjustedSchedule = periodicSchedule.createSchedule();
    Schedule unadjustedSchedule = adjustedSchedule.toUnadjusted();
    DateAdjuster exCouponPeriodAdjuster = exCouponPeriod.toDateAdjuster(refData);

    ImmutableList.Builder<FixedCouponBondPaymentPeriod> accrualPeriods = ImmutableList.builder();
    for (int i = 0; i < adjustedSchedule.size(); i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      SchedulePeriod unadjustedPeriod = SchedulePeriod.of(period.getUnadjustedStartDate(),
          period.getUnadjustedEndDate());
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
        .periodicSchedule(periodicSchedule)
        .fixedRate(fixedRate)
        .dayCount(dayCount)
        .yieldConvention(yieldConvention)
        .settlementDateOffset(settlementDateOffset.resolve(refData))
        .exCouponPeriod(exCouponPeriod.resolve(refData))
        .build();
  }