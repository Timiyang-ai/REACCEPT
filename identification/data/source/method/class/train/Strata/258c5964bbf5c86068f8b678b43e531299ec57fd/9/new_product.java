@SuppressWarnings("unchecked")
  public CalculationResults execute(ScenarioMarketData marketData, ReferenceData refData) {
    // calculate the results
    Map<Measure, Result<?>> results = calculate(marketData, refData);

    // convert the results, using a normal loop for better stack traces
    ScenarioFxRateProvider fxProvider = new DefaultScenarioFxRateProvider(marketData);
    ImmutableList.Builder<CalculationResult> resultBuilder = ImmutableList.builder();
    for (CalculationTaskCell cell : cells) {
      resultBuilder.add(cell.createResult(this, target, results, fxProvider, refData));
    }

    // return the result
    return CalculationResults.of(target, resultBuilder.build());
  }