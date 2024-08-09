CurrencyAmount presentValueWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    CurrencyAmount pvNominal = nominalPricer.presentValue(
        expanded.getNominalPayment(), discountFactors.getDiscountFactors(), zSpread, compoundedRateType, periodsPerYear);
    boolean isExCoupon = product.getExCouponPeriod().getDays() != 0;
    CurrencyAmount pvCoupon = presentValueCouponFromZSpread(
        expanded, discountFactors, zSpread, compoundedRateType, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.plus(pvCoupon);
  }