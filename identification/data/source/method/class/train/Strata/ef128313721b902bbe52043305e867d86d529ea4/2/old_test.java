  @Test
  public void test_none() {
    ScenarioPerturbation<Double> test = ScenarioPerturbation.none();
    assertThat(test.getScenarioCount()).isEqualTo(1);
    MarketDataBox<Double> box1 = MarketDataBox.ofScenarioValues(1d, 2d, 3d);
    assertThat(test.applyTo(box1, REF_DATA)).isEqualTo(box1);
    MarketDataBox<Double> box2 = MarketDataBox.ofSingleValue(1d);
    assertThat(test.applyTo(box2, REF_DATA)).isEqualTo(box2);
  }