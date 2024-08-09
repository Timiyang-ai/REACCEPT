  @Test
  public void test_zeroRatePointSensitivity() {
    LegalEntitySurvivalProbabilities test = LegalEntitySurvivalProbabilities.of(LEGAL_ENTITY, DFS);
    CreditCurveZeroRateSensitivity expected =
        CreditCurveZeroRateSensitivity.of(LEGAL_ENTITY, DFS.zeroRatePointSensitivity(DATE_AFTER));
    assertThat(test.zeroRatePointSensitivity(DATE_AFTER)).isEqualTo(expected);
  }