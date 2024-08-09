CurrencyAmount presentValue(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    CurrencyAmount pvNominal =
        nominalPricer.presentValue(expanded.getNominalPayment(), discountFactors.getDiscountFactors());
    CurrencyAmount pvCoupon =
        presentValueCoupon(expanded, discountFactors, referenceDate, product.getExCouponPeriod().getDays() != 0);
    return pvNominal.plus(pvCoupon);
  }