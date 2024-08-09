@SuppressWarnings("unchecked")
  public CalculationResult execute(CalculationEnvironment scenarioData) {
    CalculationMarketData calculationData = DefaultCalculationMarketData.of(scenarioData, marketDataMappings);
    Result<?> result = Result.wrap(() -> calculate(calculationData));
    Result<?> converted = convertToReportingCurrency(result, calculationData);
    return CalculationResult.of(target, rowIndex, columnIndex, converted);
  }