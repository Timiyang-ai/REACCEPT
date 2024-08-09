public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::timeSeries);

    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      MarketDataId<?> id;

      if (key instanceof ObservableKey) {
        id = marketDataMappings.getIdForObservableKey((ObservableKey) key);
      } else {
        id = marketDataMappings.getIdForKey(key);
      }
      requirementsBuilder.values(id);
    }
    return requirementsBuilder.build();
  }