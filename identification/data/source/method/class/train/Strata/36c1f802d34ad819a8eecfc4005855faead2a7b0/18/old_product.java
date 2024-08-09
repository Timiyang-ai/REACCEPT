public double presentValueWithSpread(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    if (period.getPaymentDate().isBefore(discountFactors.getValuationDate())) {
      return 0d;
    }
    double df = discountFactors.getDiscountFactors()
        .discountFactorWithSpread(period.getPaymentDate(), zSpread, periodic, periodsPerYear);
    return period.getFixedRate() * period.getNotional() * period.getYearFraction() * df;
  }