@Override
  public ResolvedIborFuture resolve(ReferenceData refData) {
    return ResolvedIborFuture.builder()
        .currency(currency)
        .notional(notional)
        .accrualFactor(accrualFactor)
        .lastTradeDate(lastTradeDate)
        .index(index)
        .rounding(rounding)
        .build();
  }