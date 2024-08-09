@Override
  public PointSensitivityBuilder presentValueSensitivity(PricingEnvironment env, RatePaymentPeriod period) {
    Currency ccy = period.getCurrency();
    LocalDate paymentDate = period.getPaymentDate();
    double df = env.discountFactor(period.getCurrency(), paymentDate);
    PointSensitivityBuilder fwdSensitivity = futureValueSensitivity(env, period);
    fwdSensitivity = fwdSensitivity.multipliedBy(df);
    double futureValue = futureValue(env, period);
    PointSensitivityBuilder dscSensitivity = env.discountFactorZeroRateSensitivity(ccy, paymentDate);
    dscSensitivity = dscSensitivity.multipliedBy(futureValue);
    return fwdSensitivity.combinedWith(dscSensitivity);
  }