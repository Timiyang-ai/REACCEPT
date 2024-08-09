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
          .effectiveStartDate(protectionStart.isBeginning() ? period.getStartDate().minusDays(1) : period.getStartDate())
          .effectiveEndDate(protectionStart.isBeginning() ? period.getEndDate().minusDays(1) : period.getEndDate())
          .paymentDate(period.getEndDate())
          .notional(notional)
          .currency(currency)
          .coupon(coupon)
          .yearFraction(period.yearFraction(dayCount, adjustedSchedule))
          .build());
    }
    SchedulePeriod lastPeriod = adjustedSchedule.getPeriod(nPeriods - 1);
    LocalDate accEndDate = protectionStart.isBeginning() ? lastPeriod.getEndDate().plusDays(1) : lastPeriod.getEndDate();
    accrualPeriods.add(CreditCouponPaymentPeriod.builder()
        .startDate(lastPeriod.getStartDate())
        .endDate(accEndDate)
        .effectiveStartDate(protectionStart.isBeginning() ? lastPeriod.getStartDate().minusDays(1) : lastPeriod.getStartDate())
        .effectiveEndDate(lastPeriod.getEndDate())
        .paymentDate(accrualSchedule.getBusinessDayAdjustment().adjust(lastPeriod.getEndDate(), refData))
        .notional(notional)
        .currency(currency)
        .coupon(coupon)
        .yearFraction(
            SchedulePeriod.of(lastPeriod.getStartDate(), accEndDate).yearFraction(dayCount, adjustedSchedule))
        .build());
    ImmutableList<CreditCouponPaymentPeriod> periodicPayments = accrualPeriods.build();

    return ResolvedCds.builder()
        .buySell(buySell)
        .legalEntityId(legalEntityId)
        .protectionStart(protectionStart)
        .paymentOnDefault(paymentOnDefault)
        .periodicPayments(periodicPayments)
        .protectionEndDate(lastPeriod.getEndDate())
        .settlementDateOffset(settlementDateOffset)
        .stepinDateOffset(stepinDateOffset)
        .dayCount(dayCount)
        .build();
  }