public PointSensitivityBuilder presentValueSensitivityWithSpread(
      FixedCouponBondPaymentPeriod period,
      IssuerCurveDiscountFactors discountFactors,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    if (period.getPaymentDate().isBefore(discountFactors.getValuationDate())) {
      return PointSensitivityBuilder.none();
    }
    ZeroRateSensitivity zeroSensi = discountFactors.getDiscountFactors().zeroRatePointSensitivityWithSpread(
        period.getPaymentDate(), zSpread, periodic, periodsPerYear);
    IssuerCurveZeroRateSensitivity dscSensi =
        IssuerCurveZeroRateSensitivity.of(zeroSensi, discountFactors.getLegalEntityGroup());
    return dscSensi.multipliedBy(period.getFixedRate() * period.getNotional() * period.getYearFraction());
  }