public PointSensitivityBuilder presentValueSensitivity(
      CapitalIndexedBondPaymentPeriod period,
      RatesProvider ratesProvider,
      IssuerCurveDiscountFactors issuerDiscountFactors) {

    if (period.getPaymentDate().isBefore(ratesProvider.getValuationDate())) {
      return PointSensitivityBuilder.none(); 
    }
    double rate = rateComputationFn.rate(
        period.getRateComputation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    PointSensitivityBuilder rateSensi = rateComputationFn.rateSensitivity(
        period.getRateComputation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    double df = issuerDiscountFactors.discountFactor(period.getPaymentDate());
    PointSensitivityBuilder dfSensi = issuerDiscountFactors.zeroRatePointSensitivity(period.getPaymentDate());
    double factor = period.getNotional() * period.getRealCoupon();
    return rateSensi.multipliedBy(df * factor).combinedWith(dfSensi.multipliedBy((rate + 1d) * factor));
  }