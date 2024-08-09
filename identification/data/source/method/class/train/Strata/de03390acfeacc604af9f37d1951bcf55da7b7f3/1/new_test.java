  @Test
  public void calculate() {
    ImmutableList<CalculationTarget> targets = ImmutableList.of(TARGET);
    Column column1 = Column.of(TestingMeasures.PRESENT_VALUE);
    Column column2 = Column.of(TestingMeasures.BUCKETED_PV01);
    ImmutableList<Column> columns = ImmutableList.of(column1, column2);
    CalculationRules rules = CalculationRules.of(CalculationFunctions.empty());
    MarketData md = MarketData.empty(date(2016, 6, 30));
    ScenarioMarketData smd = ScenarioMarketData.empty();

    // use of try-with-resources checks class is AutoCloseable
    try (CalculationRunner test = CalculationRunner.of(MoreExecutors.newDirectExecutorService())) {
      assertThat(test.calculate(rules, targets, columns, md, REF_DATA).get(0, 0).isFailure()).isTrue();
      assertThat(test.calculateMultiScenario(rules, targets, columns, smd, REF_DATA).get(0, 0).isFailure()).isTrue();
    }
  }