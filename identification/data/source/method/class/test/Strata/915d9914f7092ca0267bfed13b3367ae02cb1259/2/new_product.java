@Override
  public DiscountFactors discountFactors(Currency currency) {
    Curve curve = discountCurves.get(currency);
    if (curve == null) {
      throw new IllegalArgumentException("Unable to find discount curve: " + currency);
    }
    if (curve.getMetadata().getYValueType().equals(ValueType.DISCOUNT_FACTOR)) {
      return SimpleDiscountFactors.of(currency, valuationDate, curve);
    }
    return ZeroRateDiscountFactors.of(currency, valuationDate, curve);
  }