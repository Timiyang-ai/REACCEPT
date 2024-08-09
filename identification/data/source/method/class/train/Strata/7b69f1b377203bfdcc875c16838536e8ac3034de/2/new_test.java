  @Test
  public void test_price_formula() {
    double sampleVol = 0.2;
    for (int i = 0; i < NB_TEST; i++) {
      double expiryTime = VOLS.relativeTime(TEST_OPTION_EXPIRY[i]);
      for (int j = 0; j < NB_TEST; j++) {
        for (PutCall putCall : new PutCall[] {PutCall.CALL, PutCall.PUT}) {
          double price = VOLS.price(expiryTime, putCall, TEST_STRIKE[j], TEST_FORWARD, sampleVol);
          double delta = VOLS.priceDelta(expiryTime, putCall, TEST_STRIKE[j], TEST_FORWARD, sampleVol);
          double gamma = VOLS.priceGamma(expiryTime, putCall, TEST_STRIKE[j], TEST_FORWARD, sampleVol);
          double theta = VOLS.priceTheta(expiryTime, putCall, TEST_STRIKE[j], TEST_FORWARD, sampleVol);
          double vega = VOLS.priceVega(expiryTime, putCall, TEST_STRIKE[j], TEST_FORWARD, sampleVol);
          assertThat(price).isEqualTo(BlackFormulaRepository.price(TEST_FORWARD, TEST_STRIKE[j], expiryTime, sampleVol, putCall.isCall()));
          assertThat(delta).isEqualTo(BlackFormulaRepository.delta(TEST_FORWARD, TEST_STRIKE[j], expiryTime, sampleVol, putCall.isCall()));
          assertThat(gamma).isEqualTo(BlackFormulaRepository.gamma(TEST_FORWARD, TEST_STRIKE[j], expiryTime, sampleVol));
          assertThat(theta).isEqualTo(BlackFormulaRepository.driftlessTheta(TEST_FORWARD, TEST_STRIKE[j], expiryTime, sampleVol));
          assertThat(vega).isEqualTo(BlackFormulaRepository.vega(TEST_FORWARD, TEST_STRIKE[j], expiryTime, sampleVol));
        }
      }
    }
  }