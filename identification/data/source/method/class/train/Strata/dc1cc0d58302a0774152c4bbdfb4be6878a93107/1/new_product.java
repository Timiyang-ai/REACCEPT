@Override
  public double presentValue(RatesProvider provider, NotionalExchange event) {
    // futureValue * discountFactor
    double df = provider.discountFactor(event.getPaymentAmount().getCurrency(), event.getPaymentDate());
    return futureValue(provider, event) * df;
  }