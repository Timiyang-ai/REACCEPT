public CurveSensitivities withMarketDataNames(Function<MarketDataName<?>, MarketDataName<?>> nameFn) {
    return new CurveSensitivities(
        info,
        MapStream.of(typedSensitivities)
            .mapValues(sens -> sens.withMarketDataNames(nameFn))
            .toMap());
  }