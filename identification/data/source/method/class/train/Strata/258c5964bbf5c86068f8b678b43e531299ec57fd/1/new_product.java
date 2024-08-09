@SuppressWarnings("unchecked")
  public CalculationResult execute(CalculationEnvironment scenarioData) {
    CalculationMarketData calculationData = new DefaultCalculationMarketData(scenarioData, marketDataMappings);
    Result<?> result;

    try {
      if (function instanceof CalculationSingleFunction) {
        Object value = ((CalculationSingleFunction<CalculationTarget, Object>) function).execute(target, calculationData);
        result = value instanceof Result ? (Result<?>) value : Result.success(value);

      } else if (function instanceof CalculationMultiFunction) {
        ImmutableSet<Measure> measures = ImmutableSet.of(getMeasure());
        Map<Measure, Result<ScenarioResult<?>>> map =
            ((CalculationMultiFunction<CalculationTarget>) function).calculate(target, measures, calculationData);
        if (!map.containsKey(getMeasure())) {
          throw new IllegalStateException(Messages.format(
              "Function '{}' did not return requested measure '{}'", function.getClass().getName(), getMeasure()));
        }
        result = map.get(getMeasure());

      } else {
        throw new IllegalStateException(Messages.format("Unknown function type '{}'", function.getClass().getName()));
      }
    } catch (RuntimeException ex) {
      result = Result.failure(ex);
    }
    return CalculationResult.of(target, rowIndex, columnIndex, convertToReportingCurrency(result, calculationData));
  }