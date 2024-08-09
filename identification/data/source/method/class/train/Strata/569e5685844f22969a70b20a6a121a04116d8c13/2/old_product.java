@Override
  public double presentValue(KnownAmountPaymentPeriod period, RatesProvider provider) {
    return paymentPricer.presentValue(period.getPayment(), provider).getAmount();
  }