@Override
  public ResolvedBondFutureTrade resolve(ReferenceData refData) {
    ResolvedBondFuture resolved = getProduct().resolve(refData);
    return new ResolvedBondFutureTrade(tradeInfo, resolved, quantity, price);
  }