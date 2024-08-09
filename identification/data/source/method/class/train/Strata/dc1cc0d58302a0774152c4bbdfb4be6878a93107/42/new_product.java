@Override
  public PointSensitivityBuilder presentValueSensitivity(RatePaymentPeriod period, RatesProvider provider) {
    Currency ccy = period.getCurrency();
    DiscountFactors discountFactors = provider.discountFactors(ccy);
    LocalDate paymentDate = period.getPaymentDate();
    double df = discountFactors.discountFactor(paymentDate);
    PointSensitivityBuilder forecastSensitivity = forecastValueSensitivity(period, provider);
    forecastSensitivity = forecastSensitivity.multipliedBy(df);
    double forecastValue = forecastValue(period, provider);
    PointSensitivityBuilder dscSensitivity = discountFactors.zeroRatePointSensitivity(paymentDate);
    dscSensitivity = dscSensitivity.multipliedBy(forecastValue);
    return forecastSensitivity.combinedWith(dscSensitivity);
  }