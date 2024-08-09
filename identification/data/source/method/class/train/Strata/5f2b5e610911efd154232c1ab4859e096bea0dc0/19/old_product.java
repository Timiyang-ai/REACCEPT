@Override
  public ResolvedIborFixingDeposit resolve(ReferenceData refData) {
    BusinessDayAdjustment bda = getBusinessDayAdjustment().orElse(BusinessDayAdjustment.NONE);
    LocalDate start = bda.adjust(startDate);
    LocalDate end = bda.adjust(endDate);
    double yearFraction = dayCount.yearFraction(start, end);
    LocalDate fixingDate = fixingDateOffset.adjust(startDate);
    return ResolvedIborFixingDeposit.builder()
        .startDate(start)
        .endDate(end)
        .yearFraction(yearFraction)
        .currency(getCurrency())
        .notional(buySell.normalize(notional))
        .floatingRate(IborRateObservation.of(index, fixingDate))
        .fixedRate(fixedRate)
        .build();
  }