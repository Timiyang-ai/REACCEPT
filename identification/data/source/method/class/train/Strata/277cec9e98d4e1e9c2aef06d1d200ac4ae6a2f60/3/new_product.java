@Override
  public ResolvedBondFutureOption resolve(ReferenceData refData) {
    ResolvedBondFuture resolved = underlyingFuture.resolve(refData);
    return new ResolvedBondFutureOption(securityId, putCall, strikePrice, getExpiry(), premiumStyle, rounding, resolved);
  }