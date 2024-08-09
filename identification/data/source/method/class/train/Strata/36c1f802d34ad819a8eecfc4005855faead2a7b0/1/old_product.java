CurrencyAmount presentValueWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    CurrencyAmount pvNominal = nominalPricer.presentValueWithSpread(
        bond.getNominalPayment(), discountFactors.getDiscountFactors(), zSpread, compoundedRateType, periodsPerYear);
    CurrencyAmount pvCoupon = presentValueCouponFromZSpread(
        bond, discountFactors, zSpread, compoundedRateType, periodsPerYear, referenceDate);
    return pvNominal.plus(pvCoupon);
  }