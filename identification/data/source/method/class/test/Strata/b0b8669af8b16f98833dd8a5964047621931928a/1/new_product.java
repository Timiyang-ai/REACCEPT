@Override
  public ResolvedIborFuture resolve(ReferenceData refData) {
    IborRateObservation iborRate = IborRateObservation.of(index, lastTradeDate, refData);
    return new ResolvedIborFuture(securityId, currency, notional, accrualFactor, iborRate, rounding);
  }