  @Test
  public void test_parameterSensitivity() {
    LegalEntitySurvivalProbabilities test = LegalEntitySurvivalProbabilities.of(LEGAL_ENTITY, DFS);
    CreditCurveZeroRateSensitivity point =
        CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, ZeroRateSensitivity.of(USD, 1d, 1d));
    assertThat(test.parameterSensitivity(point).size()).isEqualTo(1);
  }