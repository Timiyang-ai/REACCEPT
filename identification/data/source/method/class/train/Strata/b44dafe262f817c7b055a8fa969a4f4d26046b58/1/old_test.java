  @Test
  public void test_filter() {
    CalculationParameters test = CalculationParameters.of(PARAM);
    TestTarget target = new TestTarget();

    CalculationParameters filtered1 = test.filter(target, TestingMeasures.PRESENT_VALUE);
    assertThat(filtered1.getParameters()).hasSize(1);
    assertThat(filtered1.getParameters().get(TestParameter.class)).isEqualTo(PARAM);
  }