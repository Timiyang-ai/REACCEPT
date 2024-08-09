@Override
  public CapitalIndexedBond createProduct(ReferenceData refData) {
    return new CapitalIndexedBond(
        getSecurityId(),
        currency,
        notional,
        accrualSchedule,
        rateCalculation,
        dayCount,
        yieldConvention,
        legalEntityId,
        settlementDateOffset,
        exCouponPeriod);
  }