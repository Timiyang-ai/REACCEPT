public static CurrencyParameterSensitivity combine(
      MarketDataName<?> marketDataName,
      CurrencyParameterSensitivity... sensitivities) {

    ArgChecker.notEmpty(sensitivities, "sensitivities");
    if (sensitivities.length < 2) {
      throw new IllegalArgumentException("At least two sensitivity instances must be specified");
    }
    Currency currency = Stream.of(sensitivities).map(s -> s.getCurrency()).distinct().reduce(ensureOnlyOne()).get();
    int size = Stream.of(sensitivities).mapToInt(s -> s.getParameterCount()).sum();
    double[] combinedSensitivities = new double[size];
    ImmutableList.Builder<ParameterMetadata> combinedMeta = ImmutableList.builder();
    ImmutableList.Builder<ParameterSize> split = ImmutableList.builder();
    int count = 0;
    for (int i = 0; i < sensitivities.length; i++) {
      CurrencyParameterSensitivity sens = sensitivities[i];
      System.arraycopy(sens.getSensitivity().toArrayUnsafe(), 0, combinedSensitivities, count, sens.getParameterCount());
      combinedMeta.addAll(sens.getParameterMetadata());
      split.add(ParameterSize.of(sens.getMarketDataName(), sens.getParameterCount()));
      count += sens.getParameterCount();
    }

    return new CurrencyParameterSensitivity(
        marketDataName, combinedMeta.build(), currency, DoubleArray.ofUnsafe(combinedSensitivities), split.build());
  }