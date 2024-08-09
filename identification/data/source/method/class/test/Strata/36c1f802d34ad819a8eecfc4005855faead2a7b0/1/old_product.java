public CurrencyAmount presentValue(
      Payment payment,
      DiscountFactors discountFactors,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    if (discountFactors.getValuationDate().isAfter(payment.getDate())) {
      return CurrencyAmount.zero(payment.getCurrency());
    }
    double df = discountFactors.discountFactorWithSpread(payment.getDate(), zSpread, periodic, periodsPerYear);
    return payment.getValue().multipliedBy(df);
  }