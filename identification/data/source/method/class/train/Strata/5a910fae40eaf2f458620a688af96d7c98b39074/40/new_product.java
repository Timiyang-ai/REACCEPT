@Override
  public ResolvedIborFutureTrade resolve(ReferenceData refData) {
    ResolvedIborFuture resolved = getProduct().resolve(refData);
    return new ResolvedIborFutureTrade(info, resolved, quantity, price);
  }