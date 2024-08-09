public CalculationResult execute(ScenarioCalculationEnvironment scenarioData) {
    CalculationMarketData calculationData = new DefaultCalculationMarketData(scenarioData, marketDataMappings);
    Result<?> result;

    try {
      Object value = function.execute(target, calculationData);
      result = value instanceof Result ?
          (Result<?>) value :
          Result.success(value);
    } catch (RuntimeException e) {
      result = Result.failure(e);
    }
    return CalculationResult.of(target, rowIndex, columnIndex, convertToReportingCurrency(result, calculationData));
  }