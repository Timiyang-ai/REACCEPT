public PointSensitivityBuilder presentValueSensitivity(
      CapitalIndexedBondPaymentPeriod period,
      RatesProvider ratesProvider,
      IssuerCurveDiscountFactors issuerDiscountFactors) {

    if (period.getPaymentDate().isBefore(ratesProvider.getValuationDate())) {
      return PointSensitivityBuilder.none(); 
    }
    double rate = rateObservationFn.rate(
        period.getRateObservation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    PointSensitivityBuilder rateSensi = rateObservationFn.rateSensitivity(
        period.getRateObservation(), period.getStartDate(), period.getEndDate(), ratesProvider);
    double df = issuerDiscountFactors.discountFactor(period.getPaymentDate());
    PointSensitivityBuilder dfSensi = issuerDiscountFactors.zeroRatePointSensitivity(period.getPaymentDate());
    double factor = period.getNotional() * period.getRealCoupon();
    return rateSensi.multipliedBy(df * factor).combinedWith(dfSensi.multipliedBy((rate + 1d) * factor));
  }