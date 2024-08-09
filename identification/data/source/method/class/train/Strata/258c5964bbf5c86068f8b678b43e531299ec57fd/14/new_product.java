public CalculationResult execute(ScenarioMarketData scenarioData) {
    CalculationMarketData calculationData = new DefaultCalculationMarketData(scenarioData, marketDataMappings);
    Result<?> result = Result.of(() -> function.execute(target, calculationData));
    return CalculationResult.of(target, rowIndex, columnIndex, convertToReportingCurrency(result, calculationData));
  }