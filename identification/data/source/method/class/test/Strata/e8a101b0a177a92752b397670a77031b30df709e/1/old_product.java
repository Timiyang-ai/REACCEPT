@Override
  public ResolvedFxVanillaOption resolve(ReferenceData refData) {
    return ResolvedFxVanillaOption.builder()
        .putCall(putCall)
        .longShort(longShort)
        .expiry(getExpiry())
        .underlying(underlying.resolve(refData))
        .strike(strike)
        .build();
  }