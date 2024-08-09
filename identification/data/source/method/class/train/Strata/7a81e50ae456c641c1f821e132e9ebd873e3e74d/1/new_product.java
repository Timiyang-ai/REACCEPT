@Override
  public ResolvedOvernightFuture resolve(ReferenceData refData) {
    OvernightRateComputation overnightAveragedRate = OvernightRateComputation.of(
        index, startDate, endDate, 0, accrualMethod, refData);
    return ResolvedOvernightFuture.builder()
        .securityId(securityId)
        .accrualFactor(accrualFactor)
        .currency(currency)
        .notional(notional)
        .lastTradeDate(lastTradeDate)
        .overnightRate(overnightAveragedRate)
        .rounding(rounding)
        .build();
  }