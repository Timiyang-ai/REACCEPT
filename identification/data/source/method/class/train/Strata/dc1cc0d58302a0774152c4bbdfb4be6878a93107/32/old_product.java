@Override
  public double presentValue(NotionalExchange event, RatesProvider provider) {
    return paymentPricer.presentValue(event.getPayment(), provider).getAmount();
  }