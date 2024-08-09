@Override
  public ResolvedIborFutureTrade resolve(ReferenceData refData) {
    ResolvedIborFuture resolved = getProduct().resolve(refData);
    return new ResolvedIborFutureTrade(tradeInfo, resolved, quantity, price);
  }