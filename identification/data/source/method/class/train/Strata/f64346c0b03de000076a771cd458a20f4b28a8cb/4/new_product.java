@Override
  public ResolvedBill resolve(ReferenceData refData) {
    return ResolvedBill.builder()
        .securityId(securityId)
        .notional(notional.resolve(refData))
        .dayCount(dayCount)
        .yieldConvention(yieldConvention)
        .legalEntityId(legalEntityId)
        .settlementDateOffset(settlementDateOffset).build();
  }