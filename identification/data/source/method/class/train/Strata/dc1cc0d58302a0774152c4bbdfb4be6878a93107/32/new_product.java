@Override
  public double presentValue(NotionalExchange event, RatesProvider provider) {
    return paymentPricer.presentValueAmount(event.getPayment(), provider);
  }