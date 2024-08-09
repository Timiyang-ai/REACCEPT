  @Test
  public void test_requirements() {
    CalculationFunctions functions = CalculationFunctions.of(ImmutableMap.of(TestTarget.class, new TestFunction()));
    CalculationRules calculationRules = CalculationRules.of(functions, USD);
    List<TestTarget> targets = ImmutableList.of(TARGET1);
    List<Column> columns = ImmutableList.of(Column.of(TestingMeasures.PRESENT_VALUE));

    CalculationTasks test = CalculationTasks.of(calculationRules, targets, columns);

    MarketDataRequirements requirements = test.requirements(REF_DATA);
    Set<? extends MarketDataId<?>> nonObservables = requirements.getNonObservables();
    ImmutableSet<? extends ObservableId> observables = requirements.getObservables();
    ImmutableSet<ObservableId> timeSeries = requirements.getTimeSeries();

    assertThat(nonObservables).hasSize(1);
    assertThat(nonObservables.iterator().next()).isEqualTo(TestId.of("1"));

    MarketDataId<?> observableId = TestObservableId.of("2", CalculationTaskTest.OBS_SOURCE);
    assertThat(observables).hasSize(1);
    assertThat(observables.iterator().next()).isEqualTo(observableId);

    MarketDataId<?> timeSeriesId = TestObservableId.of("3", CalculationTaskTest.OBS_SOURCE);
    assertThat(timeSeries).hasSize(1);
    assertThat(timeSeries.iterator().next()).isEqualTo(timeSeriesId);
  }