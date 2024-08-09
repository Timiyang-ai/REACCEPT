@Override
  public ResolvedFra resolve(ReferenceData refData) {
    DateAdjuster bda = getBusinessDayAdjustment().orElse(BusinessDayAdjustment.NONE).resolve(refData);
    LocalDate start = bda.adjust(startDate);
    LocalDate end = bda.adjust(endDate);
    return ResolvedFra.builder()
        .paymentDate(getPaymentDate().adjusted(refData))
        .startDate(start)
        .endDate(end)
        .yearFraction(dayCount.yearFraction(start, end))
        .fixedRate(fixedRate)
        .floatingRate(createRateComputation(refData))
        .currency(currency)
        .notional(buySell.normalize(notional))
        .discounting(discounting)
        .build();
  }