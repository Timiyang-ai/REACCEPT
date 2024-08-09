@Override
  public PointSensitivityBuilder presentValueSensitivity(RatesProvider provider, RatePaymentPeriod period) {
    Currency ccy = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    double df = provider.discountFactor(period.getCurrency(), paymentDate);
    PointSensitivityBuilder fwdSensitivity = futureValueSensitivity(provider, period);
    fwdSensitivity = fwdSensitivity.multipliedBy(df);
    double futureValue = futureValue(provider, period);
    PointSensitivityBuilder dscSensitivity = provider.discountFactorZeroRateSensitivity(ccy, paymentDate);
    dscSensitivity = dscSensitivity.multipliedBy(futureValue);
    return fwdSensitivity.combinedWith(dscSensitivity);
  }