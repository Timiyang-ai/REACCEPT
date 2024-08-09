@Override
  public PointSensitivityBuilder presentValueSensitivity(KnownAmountSwapPaymentPeriod period, RatesProvider provider) {
    return paymentPricer.presentValueSensitivity(period.getPayment(), provider);
  }