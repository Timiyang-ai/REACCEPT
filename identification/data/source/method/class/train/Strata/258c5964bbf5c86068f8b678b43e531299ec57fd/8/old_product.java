@SuppressWarnings("unchecked")
  public CalculationResults execute(CalculationEnvironment scenarioData, ReferenceData refData) {
    CalculationMarketData calculationData = DefaultCalculationMarketData.of(scenarioData, marketDataMappings);
    Result<?> result = Result.wrap(() -> calculate(calculationData, refData));
    Result<?> converted = convertToReportingCurrency(result, calculationData, refData);
    CalculationResult calcResult = CalculationResult.of(rowIndex, columnIndex, converted);
    return CalculationResults.of(target, ImmutableList.of(calcResult));
  }