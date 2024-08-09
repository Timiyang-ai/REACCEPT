@Override
  public double presentValue(RatesProvider provider, FxResetNotionalExchange event) {
    // futureValue * discountFactor
    double df = provider.discountFactor(event.getCurrency(), event.getPaymentDate());
    return futureValue(provider, event) * df;
  }