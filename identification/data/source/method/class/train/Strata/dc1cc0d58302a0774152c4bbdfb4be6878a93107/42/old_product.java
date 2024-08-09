@Override
  public PointSensitivityBuilder presentValueSensitivity(RatePaymentPeriod period, RatesProvider provider) {
    Currency ccy = period.getCurrency();
    DiscountFactors discountFactors = provider.discountFactors(ccy);
    LocalDate paymentDate = period.getPaymentDate();
    double df = discountFactors.discountFactor(paymentDate);
    PointSensitivityBuilder fwdSensitivity = futureValueSensitivity(period, provider);
    fwdSensitivity = fwdSensitivity.multipliedBy(df);
    double futureValue = futureValue(period, provider);
    PointSensitivityBuilder dscSensitivity = discountFactors.zeroRatePointSensitivity(paymentDate);
    dscSensitivity = dscSensitivity.multipliedBy(futureValue);
    return fwdSensitivity.combinedWith(dscSensitivity);
  }