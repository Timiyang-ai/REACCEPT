  @Test
  public void test_mergedWith_map_empty() {
    CurveSensitivities base = sut();
    Map<CurveSensitivitiesType, CurrencyParameterSensitivities> additional = ImmutableMap.of();
    CurveSensitivities test = base.mergedWith(additional);
    assertThat(test).isEqualTo(base);
  }