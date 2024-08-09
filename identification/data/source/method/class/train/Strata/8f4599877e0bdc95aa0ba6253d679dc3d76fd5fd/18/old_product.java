public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::timeSeries);

    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      requirementsBuilder.values(marketDataMappings.getIdForKey(key));
    }
    return requirementsBuilder.build();
  }