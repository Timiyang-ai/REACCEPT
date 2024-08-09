@Override
  public double presentValue(KnownAmountSwapPaymentPeriod period, RatesProvider provider) {
    return paymentPricer.presentValueAmount(period.getPayment(), provider);
  }