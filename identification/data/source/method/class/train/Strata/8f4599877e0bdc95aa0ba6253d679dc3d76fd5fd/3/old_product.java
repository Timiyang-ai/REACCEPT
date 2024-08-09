public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::timeSeries);

    // This might be possible using streams but I can't figure out how to do it
    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      MarketDataId<?> id = marketDataMappings.getIdForKey(key);
      requirementsBuilder.values(id);
    }
    return requirementsBuilder.build();
  }