@SuppressWarnings("unchecked")
  public CalculationResult execute(CalculationEnvironment scenarioData) {
    CalculationMarketData calculationData = new DefaultCalculationMarketData(scenarioData, marketDataMappings);
    Result<?> result = Result.wrap(() -> calculate(calculationData));
    Result<?> converted = convertToReportingCurrency(result, calculationData);
    return CalculationResult.of(target, rowIndex, columnIndex, converted);
  }