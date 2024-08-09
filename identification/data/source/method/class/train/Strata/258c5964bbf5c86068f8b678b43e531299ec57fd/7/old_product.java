public Result<?> execute(ScenarioMarketData marketData) {
    try {
      DefaultCalculationMarketData calculationData = new DefaultCalculationMarketData(marketData, marketDataMappings);
      return Result.success(function.execute(target, calculationData, reportingRules));
    } catch (Exception e) {
      return Result.failure(e);
    }
  }