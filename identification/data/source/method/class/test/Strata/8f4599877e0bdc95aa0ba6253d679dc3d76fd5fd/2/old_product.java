public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    ImmutableSet.Builder<ObservableId> timeSeriesReqsBuilder = ImmutableSet.builder();
    ImmutableSet.Builder<MarketDataId<?>> singleValueReqsBuilder = ImmutableSet.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .forEach(key -> timeSeriesReqsBuilder.add(marketDataMappings.getIdForObservableKey(key)));

    // This should be possible using streams but I can't persuade the type inference to handle it
    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      MarketDataId<?> id = marketDataMappings.getIdForKey(key);
      singleValueReqsBuilder.add(id);
    }
    return MarketDataRequirements.builder()
        .timeSeriesRequirements(timeSeriesReqsBuilder.build())
        .singleValueRequirements(singleValueReqsBuilder.build())
        .build();
  }