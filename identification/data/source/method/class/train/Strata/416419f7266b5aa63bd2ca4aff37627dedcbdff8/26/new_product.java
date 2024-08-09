public PointSensitivityBuilder presentValueSensitivity(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider) {

    return presentValueSensitivity(bond, provider, provider.getValuationDate());
  }