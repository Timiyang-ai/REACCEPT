public PointSensitivityBuilder presentValueSensitivity(
      Payment payment, 
      DiscountFactors discountFactors,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    if (discountFactors.getValuationDate().isAfter(payment.getDate())) {
      return PointSensitivityBuilder.none();
    }
    ZeroRateSensitivity sensi =
        discountFactors.zeroRatePointSensitivityWithSpread(payment.getDate(), zSpread, periodic, periodsPerYear);
    return sensi.multipliedBy(payment.getAmount());
  }