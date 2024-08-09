@Override
  public ResolvedIborFutureOption resolve(ReferenceData refData) {
    return ResolvedIborFutureOption.builder()
        .putCall(putCall)
        .strikePrice(strikePrice)
        .expiry(getExpiry())
        .premiumStyle(premiumStyle)
        .rounding(rounding)
        .underlying(getUnderlying().resolve(refData))
        .build();
  }