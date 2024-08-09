public PointSensitivityBuilder presentValueSensitivity(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider) {

    return presentValueSensitivity(product, provider, provider.getValuationDate());
  }