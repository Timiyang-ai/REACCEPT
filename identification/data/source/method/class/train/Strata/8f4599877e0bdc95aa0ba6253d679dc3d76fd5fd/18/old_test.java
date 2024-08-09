  @Test
  public void requirements() {
    CalculationTaskCell cell = CalculationTaskCell.of(0, 0, TestingMeasures.PRESENT_VALUE, NATURAL);
    CalculationTask task = CalculationTask.of(TARGET, new TestFunction(), cell);
    MarketDataRequirements requirements = task.requirements(REF_DATA);
    Set<? extends MarketDataId<?>> nonObservables = requirements.getNonObservables();
    ImmutableSet<? extends ObservableId> observables = requirements.getObservables();
    ImmutableSet<ObservableId> timeSeries = requirements.getTimeSeries();

    MarketDataId<?> timeSeriesId = TestObservableId.of("3", OBS_SOURCE);
    assertThat(timeSeries).hasSize(1);
    assertThat(timeSeries.iterator().next()).isEqualTo(timeSeriesId);

    MarketDataId<?> nonObservableId = new TestId("1");
    assertThat(nonObservables).hasSize(1);
    assertThat(nonObservables.iterator().next()).isEqualTo(nonObservableId);

    MarketDataId<?> observableId = TestObservableId.of("2", OBS_SOURCE);
    assertThat(observables).hasSize(1);
    assertThat(observables.iterator().next()).isEqualTo(observableId);
  }