@Override
  public PointSensitivityBuilder presentValueSensitivity(KnownAmountPaymentPeriod period, RatesProvider provider) {
    return paymentPricer.presentValueSensitivity(period.getPayment(), provider);
  }