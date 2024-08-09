@Override
  public DiscountFactors discountFactors(Currency currency) {
    Curve curve = discountCurves.get(currency);
    if (curve == null) {
      throw new IllegalArgumentException("Unable to find discount curve: " + currency);
    }
    return DiscountFactors.of(currency, valuationDate, curve);
  }