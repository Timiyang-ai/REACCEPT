@Override
  public ResolvedIborFutureOption resolve(ReferenceData refData) {
    ResolvedIborFuture resolved = underlyingFuture.resolve(refData);
    return new ResolvedIborFutureOption(securityId, putCall, strikePrice, getExpiry(), premiumStyle, rounding, resolved);
  }