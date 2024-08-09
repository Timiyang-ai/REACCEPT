@Override
  public PointSensitivityBuilder presentValueSensitivity(RatePaymentPeriod period, RatesProvider provider) {
    Currency ccy = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    double df = provider.discountFactor(period.getCurrency(), paymentDate);
    PointSensitivityBuilder fwdSensitivity = futureValueSensitivity(period, provider);
    fwdSensitivity = fwdSensitivity.multipliedBy(df);
    double futureValue = futureValue(period, provider);
    PointSensitivityBuilder dscSensitivity = provider.discountFactorZeroRateSensitivity(ccy, paymentDate);
    dscSensitivity = dscSensitivity.multipliedBy(futureValue);
    return fwdSensitivity.combinedWith(dscSensitivity);
  }