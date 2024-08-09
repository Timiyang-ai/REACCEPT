@Override
  public ResolvedIborFuture resolve(ReferenceData refData) {
    return ResolvedIborFuture.builder()
        .currency(currency)
        .notional(notional)
        .accrualFactor(accrualFactor)
        .observation(IborRateObservation.of(index, lastTradeDate, refData))
        .rounding(rounding)
        .build();
  }