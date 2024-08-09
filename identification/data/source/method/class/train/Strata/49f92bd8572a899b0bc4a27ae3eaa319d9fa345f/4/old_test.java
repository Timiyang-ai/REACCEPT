  @Test
  public void test_volatility() {
    for (int i = 0; i < NB_TEST; i++) {
      double expiryTime = VOLS.relativeTime(TEST_OPTION_EXPIRY[i]);
      double volExpected = SURFACE.zValue(expiryTime, TEST_TENOR[i]);
      double volComputed = VOLS.volatility(TEST_OPTION_EXPIRY[i], TEST_TENOR[i], TEST_STRIKE, TEST_FORWARD);
      assertThat(volComputed).isCloseTo(volExpected, offset(TOLERANCE_VOL));
    }
  }