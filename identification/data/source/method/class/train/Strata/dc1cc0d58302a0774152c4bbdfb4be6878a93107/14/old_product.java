@Override
  public double presentValue(NotionalExchange event, RatesProvider provider) {
    // forecastValue * discountFactor
    double df = provider.discountFactor(event.getCurrency(), event.getPaymentDate());
    return forecastValue(event, provider) * df;
  }