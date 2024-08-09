@Override
  public ResolvedCdsIndex resolve(ReferenceData refData) {
    Schedule adjustedSchedule = accrualSchedule.createSchedule(refData);
    ImmutableList.Builder<CreditCouponPaymentPeriod> accrualPeriods = ImmutableList.builder();
    int nPeriods = adjustedSchedule.size();
    for (int i = 0; i < nPeriods - 1; i++) {
      SchedulePeriod period = adjustedSchedule.getPeriod(i);
      accrualPeriods.add(CreditCouponPaymentPeriod.builder()
          .startDate(period.getStartDate())
          .endDate(period.getEndDate())
          .unadjustedStartDate(period.getUnadjustedStartDate())
          .unadjustedEndDate(period.getUnadjustedEndDate())
          .effectiveStartDate(protectionStart.isBeginning() ? period.getStartDate().minusDays(1) : period.getStartDate())
          .effectiveEndDate(protectionStart.isBeginning() ? period.getEndDate().minusDays(1) : period.getEndDate())
          .paymentDate(period.getEndDate())
          .notional(notional)
          .currency(currency)
          .fixedRate(fixedRate)
          .yearFraction(period.yearFraction(dayCount, adjustedSchedule))
          .build());
    }
    SchedulePeriod lastPeriod = adjustedSchedule.getPeriod(nPeriods - 1);
    LocalDate accEndDate = protectionStart.isBeginning() ? lastPeriod.getEndDate().plusDays(1) : lastPeriod.getEndDate();
    SchedulePeriod modifiedPeriod = lastPeriod.toBuilder().endDate(accEndDate).build();
    accrualPeriods.add(CreditCouponPaymentPeriod.builder()
        .startDate(modifiedPeriod.getStartDate())
        .endDate(modifiedPeriod.getEndDate())
        .unadjustedStartDate(modifiedPeriod.getUnadjustedStartDate())
        .unadjustedEndDate(modifiedPeriod.getUnadjustedEndDate())
        .effectiveStartDate(protectionStart.isBeginning() ? lastPeriod.getStartDate().minusDays(1) : lastPeriod.getStartDate())
        .effectiveEndDate(lastPeriod.getEndDate())
        .paymentDate(accrualSchedule.getBusinessDayAdjustment().adjust(lastPeriod.getEndDate(), refData))
        .notional(notional)
        .currency(currency)
        .fixedRate(fixedRate)
        .yearFraction(modifiedPeriod.yearFraction(dayCount, adjustedSchedule))
        .build());
    ImmutableList<CreditCouponPaymentPeriod> periodicPayments = accrualPeriods.build();

    return ResolvedCdsIndex.builder()
        .buySell(buySell)
        .cdsIndexId(cdsIndexId)
        .legalEntityIds(legalEntityIds)
        .protectionStart(protectionStart)
        .paymentOnDefault(paymentOnDefault)
        .periodicPayments(periodicPayments)
        .protectionEndDate(lastPeriod.getEndDate())
        .settlementDateOffset(settlementDateOffset)
        .stepinDateOffset(stepinDateOffset)
        .dayCount(dayCount)
        .build();
  }