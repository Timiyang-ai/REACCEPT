@Override
  public double presentValue(KnownAmountPaymentPeriod period, RatesProvider provider) {
    return paymentPricer.presentValueAmount(period.getPayment(), provider);
  }