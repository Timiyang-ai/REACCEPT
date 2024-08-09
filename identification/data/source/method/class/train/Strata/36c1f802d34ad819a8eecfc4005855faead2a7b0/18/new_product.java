public double presentValueWithSpread(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    if (period.getPaymentDate().isBefore(discountFactors.getValuationDate())) {
      return 0d;
    }
    double df = discountFactors.getDiscountFactors()
        .discountFactorWithSpread(period.getPaymentDate(), zSpread, compoundedRateType, periodsPerYear);
    return period.getFixedRate() * period.getNotional() * period.getYearFraction() * df;
  }