public CurrencyAmount presentValue(
      Payment payment,
      DiscountFactors discountFactors,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    if (discountFactors.getValuationDate().isAfter(payment.getDate())) {
      return CurrencyAmount.zero(payment.getCurrency());
    }
    double df = discountFactors.discountFactorWithSpread(payment.getDate(), zSpread, compoundedRateType, periodsPerYear);
    return payment.getValue().multipliedBy(df);
  }