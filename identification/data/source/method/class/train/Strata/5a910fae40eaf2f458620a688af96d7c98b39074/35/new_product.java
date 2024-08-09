@Override
  public ResolvedIborFutureOptionTrade resolve(ReferenceData refData) {
    ResolvedIborFutureOption resolved = getProduct().resolve(refData);
    return new ResolvedIborFutureOptionTrade(info, resolved, quantity, price);
  }