@SuppressWarnings("unchecked")
  public CalculationResult execute(CalculationEnvironment scenarioData, ReferenceData refData) {
    CalculationMarketData calculationData = DefaultCalculationMarketData.of(scenarioData, marketDataMappings);
    Result<?> result = Result.wrap(() -> calculate(calculationData, refData));
    Result<?> converted = convertToReportingCurrency(result, calculationData);
    return CalculationResult.of(target, rowIndex, columnIndex, converted);
  }