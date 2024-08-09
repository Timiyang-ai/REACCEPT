@Override
  public ResolvedBondFutureOptionTrade resolve(ReferenceData refData) {
    ResolvedBondFutureOption resolved = getProduct().resolve(refData);
    return new ResolvedBondFutureOptionTrade(info, resolved, quantity, price);
  }