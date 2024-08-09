@Override
  public ResolvedIborFutureOptionTrade resolve(ReferenceData refData) {
    ResolvedIborFutureOption resolved = getProduct().resolve(refData);
    return new ResolvedIborFutureOptionTrade(tradeInfo, resolved, quantity, price);
  }