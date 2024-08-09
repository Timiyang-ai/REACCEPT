@Override
  public double presentValue(NotionalExchange event, RatesProvider provider) {
    // futureValue * discountFactor
    double df = provider.discountFactor(event.getPaymentAmount().getCurrency(), event.getPaymentDate());
    return futureValue(event, provider) * df;
  }