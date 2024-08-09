@Override
  public ResolvedFxVanillaOption resolve(ReferenceData refData) {
    return ResolvedFxVanillaOption.builder()
        .longShort(longShort)
        .expiry(getExpiry())
        .underlying(underlying.resolve(refData))
        .build();
  }