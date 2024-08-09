CurrencyAmount presentValue(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    CurrencyAmount pvNominal =
        nominalPricer.presentValue(bond.getNominalPayment(), discountFactors.getDiscountFactors());
    CurrencyAmount pvCoupon = presentValueCoupon(bond, discountFactors, referenceDate);
    return pvNominal.plus(pvCoupon);
  }