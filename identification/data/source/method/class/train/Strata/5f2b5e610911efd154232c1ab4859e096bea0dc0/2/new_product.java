@Override
  public ResolvedIborFixingDeposit resolve(ReferenceData refData) {
    DateAdjuster bda = getBusinessDayAdjustment().orElse(BusinessDayAdjustment.NONE).toDateAdjuster(refData);
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
        .floatingRate(IborRateObservation.of(index, fixingDate, refData))
        .fixedRate(fixedRate)
        .build();
  }