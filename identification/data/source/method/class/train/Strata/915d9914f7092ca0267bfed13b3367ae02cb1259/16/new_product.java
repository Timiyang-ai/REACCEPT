@Override
  public DiscountFactors discountFactors(Currency currency) {
    DiscountFactors df = discountFactors.get(currency);
    if (df == null) {
      throw new IllegalArgumentException("Unable to find discount factors: " + currency);
    }
    return df;
  }