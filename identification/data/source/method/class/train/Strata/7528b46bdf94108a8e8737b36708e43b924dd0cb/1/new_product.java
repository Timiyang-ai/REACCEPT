@Override
  public ImmutableSet<Currency> allPaymentCurrencies() {
    return legs.stream().map(leg -> leg.getCurrency()).collect(toImmutableSet());
  }