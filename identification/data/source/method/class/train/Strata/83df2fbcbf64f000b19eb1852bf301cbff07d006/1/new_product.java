@Override
  public double forecastValue(NotionalExchange event, RatesProvider provider) {
    return paymentPricer.forecastValueAmount(event.getPayment(), provider);
  }