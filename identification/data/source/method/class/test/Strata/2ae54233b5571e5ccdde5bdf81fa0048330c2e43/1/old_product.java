@Override
  public ResolvedSwaption resolve(ReferenceData refData) {
    return ResolvedSwaption.builder()
        .expiry(expiryDate.adjusted().atTime(expiryTime).atZone(expiryZone))
        .longShort(longShort)
        .swaptionSettlement(swaptionSettlement)
        .underlying(underlying.resolve(refData))
        .build();
  }