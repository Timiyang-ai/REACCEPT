@Override
  public ResolvedIborFuture resolve(ReferenceData refData) {
    IborRateComputation iborRate = IborRateComputation.of(index, lastTradeDate, refData);
    return new ResolvedIborFuture(securityId, currency, notional, accrualFactor, iborRate, rounding);
  }