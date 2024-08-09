public static CurrencyParameterSensitivity combine(
      MarketDataName<?> marketDataName,
      CurrencyParameterSensitivity... sensitivities) {

    ArgChecker.notEmpty(sensitivities, "sensitivities");
    Currency currency = Stream.of(sensitivities).map(s -> s.getCurrency()).distinct().reduce(ensureOnlyOne()).get();
    int size = Stream.of(sensitivities).mapToInt(s -> s.getParameterCount()).sum();

    if (sensitivities.length == 1) {
      CurrencyParameterSensitivity first = sensitivities[0];
      if (first.getMarketDataName().equals(marketDataName)) {
        return first;
      }
    }

    double[] combinedSensitivities = new double[size];
    ImmutableList.Builder<ParameterMetadata> combinedMeta = ImmutableList.builder();
    ImmutableList.Builder<ParameterSize> split = ImmutableList.builder();
    int count = 0;
    for (int i = 0; i < sensitivities.length; i++) {
      CurrencyParameterSensitivity sens = sensitivities[i];
      if (sens.getParameterCount() == 0) {
        // Discard empty sensitivities
        continue;
      }
      System.arraycopy(sens.getSensitivity().toArrayUnsafe(), 0, combinedSensitivities, count, sens.getParameterCount());
      combinedMeta.addAll(sens.getParameterMetadata());
      split.add(ParameterSize.of(sens.getMarketDataName(), sens.getParameterCount()));
      count += sens.getParameterCount();
    }

    List<ParameterSize> parameterSplit = split.build();
    return new CurrencyParameterSensitivity(
        marketDataName,
        combinedMeta.build(),
        currency,
        DoubleArray.ofUnsafe(combinedSensitivities),
        parameterSplit.isEmpty() ? null : parameterSplit);
  }