@Override
  public ResolvedCds resolve(ReferenceData refData) {
    Schedule adjustedSchedule = accrualSchedule.createSchedule(refData);
    ImmutableList.Builder<CreditCouponPaymentPeriod> accrualPeriods = ImmutableList.builder();
    int nPeriods = adjustedSchedule.size();
    for (int i = 0; i < nPeriods - 1; i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      accrualPeriods.add(CreditCouponPaymentPeriod.builder()
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .paymentDate(period.getEndDate())
          .notional(notional)
          .currency(currency)
          .coupon(coupon)
          .yearFraction(period.yearFraction(dayCount, adjustedSchedule))
          .build());
    }
    SchedulePeriod lastPeriod = adjustedSchedule.getPeriod(nPeriods - 1);
    accrualPeriods.add(CreditCouponPaymentPeriod.builder()
        .startDate(lastPeriod.getStartDate())
        .endDate(lastPeriod.getEndDate())
        .paymentDate(accrualSchedule.getBusinessDayAdjustment().adjust(lastPeriod.getEndDate(), refData))
        .notional(notional)
        .currency(currency)
        .coupon(coupon)
        .yearFraction(lastPeriod.yearFraction(dayCount, adjustedSchedule))
        .build());
    ImmutableList<CreditCouponPaymentPeriod> periodicPayments = accrualPeriods.build();

    return ResolvedCds.builder()
        .buySell(buySell)
        .legalEntityId(legalEntityId)
        .protectStart(protectStart)
        .payAccruedOnDefault(payAccruedOnDefault)
        .periodicPayments(periodicPayments)
        .settlementDateOffset(settlementDateOffset)
        .build();
  }