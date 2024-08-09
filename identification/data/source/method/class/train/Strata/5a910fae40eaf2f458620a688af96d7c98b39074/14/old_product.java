@Override
  public ResolvedBondFutureOptionTrade resolve(ReferenceData refData) {
    ResolvedBondFutureOption resolved = getProduct().resolve(refData);
    return new ResolvedBondFutureOptionTrade(tradeInfo, resolved, quantity, price);
  }