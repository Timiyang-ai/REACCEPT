@Override
  public ResolvedBondFutureOption resolve(ReferenceData refData) {
    return ResolvedBondFutureOption.builder()
        .putCall(putCall)
        .strikePrice(strikePrice)
        .expiry(getExpiry())
        .premiumStyle(premiumStyle)
        .rounding(rounding)
        .underlying(getUnderlying().resolve(refData))
        .underlyingSecurityStandardId(getUnderlyingSecurity().getStandardId())
        .build();
  }