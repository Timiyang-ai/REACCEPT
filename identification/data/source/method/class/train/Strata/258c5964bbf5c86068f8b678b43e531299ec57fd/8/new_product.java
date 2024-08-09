@SuppressWarnings("unchecked")
  public CalculationResults execute(CalculationEnvironment marketData, ReferenceData refData) {
    // use the mappings to filter the complete market data to the subset needed here
    CalculationMarketData selectedMarketData = DefaultCalculationMarketData.of(marketData, marketDataMappings);

    // calculate the results
    Map<Measure, Result<?>> results = calculate(selectedMarketData, refData);

    // convert the results, using a normal loop for better stack traces
    ImmutableList.Builder<CalculationResult> resultBuilder = ImmutableList.builder();
    for (CalculationTaskCell cell : cells) {
      resultBuilder.add(cell.createResult(this, target, results, selectedMarketData, refData));
    }

    // return the result
    return CalculationResults.of(target, resultBuilder.build());
  }