  @Test
  public void test_volatility() {
    SabrParametersSwaptionVolatilities prov = SabrParametersSwaptionVolatilities.of(NAME, CONV, DATE_TIME, PARAM);
    for (int i = 0; i < NB_TEST; i++) {
      for (int j = 0; j < NB_STRIKE; ++j) {
        double expiryTime = prov.relativeTime(TEST_OPTION_EXPIRY[i]);
        double volExpected = PARAM.volatility(expiryTime, TEST_TENOR[i], TEST_STRIKE[j], TEST_FORWARD);
        double volComputed = prov.volatility(TEST_OPTION_EXPIRY[i], TEST_TENOR[i], TEST_STRIKE[j], TEST_FORWARD);
        assertThat(volComputed).isCloseTo(volExpected, offset(TOLERANCE_VOL));
      }
    }
  }