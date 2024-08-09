public PointSensitivityBuilder presentValueSensitivity(
      Payment payment, 
      DiscountFactors discountFactors,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    if (discountFactors.getValuationDate().isAfter(payment.getDate())) {
      return PointSensitivityBuilder.none();
    }
    ZeroRateSensitivity sensi =
        discountFactors.zeroRatePointSensitivityWithSpread(payment.getDate(), zSpread, compoundedRateType, periodsPerYear);
    return sensi.multipliedBy(payment.getAmount());
  }