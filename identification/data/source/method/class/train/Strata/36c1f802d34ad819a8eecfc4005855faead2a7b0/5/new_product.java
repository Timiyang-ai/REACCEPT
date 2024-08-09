CurrencyAmount presentValueWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    CurrencyAmount pvNominal = nominalPricer.presentValue(
        bond.getNominalPayment(), discountFactors.getDiscountFactors(), zSpread, compoundedRateType, periodsPerYear);
    boolean isExCoupon = bond.getExCouponPeriod().getDays() != 0;
    CurrencyAmount pvCoupon = presentValueCouponFromZSpread(
        bond, discountFactors, zSpread, compoundedRateType, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.plus(pvCoupon);
  }