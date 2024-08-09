CurrencyAmount presentValueWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    CurrencyAmount pvNominal = nominalPricer.presentValue(
        expanded.getNominalPayment(), discountFactors.getDiscountFactors(), zSpread, periodic, periodsPerYear);
    boolean isExCoupon = product.getExCouponPeriod().getDays() != 0;
    CurrencyAmount pvCoupon = presentValueCouponFromZSpread(
        expanded, discountFactors, zSpread, periodic, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.plus(pvCoupon);
  }