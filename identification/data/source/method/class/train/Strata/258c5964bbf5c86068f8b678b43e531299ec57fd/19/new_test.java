  @Test
  public void execute() {
    SupplierFunction<String> fn = SupplierFunction.of(() -> "foo");
    CalculationTaskCell cell = CalculationTaskCell.of(0, 0, TestingMeasures.PRESENT_VALUE, REPORTING_CURRENCY_USD);
    CalculationTask task = CalculationTask.of(TARGET, fn, cell);
    ScenarioMarketData marketData = ImmutableScenarioMarketData.builder(date(2011, 3, 8)).build();

    CalculationResults calculationResults = task.execute(marketData, REF_DATA);
    Result<?> result = calculationResults.getCells().get(0).getResult();
    assertThat(result).hasValue(ScenarioArray.of("foo"));
  }