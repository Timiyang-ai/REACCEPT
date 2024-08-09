@Override
  public ResolvedBill resolve(ReferenceData refData) {
    return ResolvedBill.builder()
        .securityId(securityId)
        .currency(currency)
        .notional(notional)
        .maturityDate(maturityDate.adjusted(refData))
        .dayCount(dayCount)
        .yieldConvention(yieldConvention)
        .legalEntityId(legalEntityId)
        .settlementDateOffset(settlementDateOffset).build();
  }