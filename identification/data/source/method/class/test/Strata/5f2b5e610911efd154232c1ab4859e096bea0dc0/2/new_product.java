@Override
  public ResolvedTermDeposit resolve(ReferenceData refData) {
    DateAdjuster bda = getBusinessDayAdjustment().orElse(BusinessDayAdjustment.NONE).resolve(refData);
    LocalDate start = bda.adjust(startDate);
    LocalDate end = bda.adjust(endDate);
    double yearFraction = dayCount.yearFraction(start, end);
    return ResolvedTermDeposit.builder()
        .startDate(start)
        .endDate(end)
        .yearFraction(yearFraction)
        .currency(getCurrency())
        .notional(buySell.normalize(notional))
        .rate(rate)
        .build();
  }