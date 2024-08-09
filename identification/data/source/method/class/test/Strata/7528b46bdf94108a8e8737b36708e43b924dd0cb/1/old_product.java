public ImmutableSet<Currency> allCurrencies() {
    ImmutableSet.Builder<Currency> builder = ImmutableSet.builder();
    legs.stream().forEach(leg -> leg.collectCurrencies(builder));
    return builder.build();
  }