public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      CapitalIndexedBondPaymentPeriod period,
      RatesProvider ratesProvider,
      IssuerCurveDiscountFactors issuerDiscountFactors,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    if (period.getPaymentDate().isBefore(ratesProvider.getValuationDate())) {
      return PointSensitivityBuilder.none();
    }
    double rate = rateComputationFn.rate(
        period.getRateComputation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    PointSensitivityBuilder rateSensi = rateComputationFn.rateSensitivity(
        period.getRateComputation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    double df = issuerDiscountFactors.getDiscountFactors()
        .discountFactorWithSpread(period.getPaymentDate(), zSpread, compoundedRateType, periodsPerYear);
    ZeroRateSensitivity zeroSensi = issuerDiscountFactors.getDiscountFactors()
        .zeroRatePointSensitivityWithSpread(period.getPaymentDate(), zSpread, compoundedRateType, periodsPerYear);
    IssuerCurveZeroRateSensitivity dfSensi =
        IssuerCurveZeroRateSensitivity.of(zeroSensi, issuerDiscountFactors.getLegalEntityGroup());
    double factor = period.getNotional() * period.getRealCoupon();
    return rateSensi.multipliedBy(df * factor).combinedWith(dfSensi.multipliedBy((rate + 1d) * factor));
  }