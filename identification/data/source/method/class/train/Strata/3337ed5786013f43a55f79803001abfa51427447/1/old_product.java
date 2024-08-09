public CurveSensitivities withMarketDataNames(Function<MarketDataName<?>, MarketDataName<?>> nameFn) {
    return new CurveSensitivities(
        info,
        MapStream.of(typedSensitivities)
            .mapValues(sens -> CurrencyParameterSensitivities.of(
                sens.getSensitivities().stream()
                    .map(single -> single.toBuilder()
                        .marketDataName(nameFn.apply(single.getMarketDataName()))
                        .build())
                    .collect(toImmutableList())))
            .toMap());
  }