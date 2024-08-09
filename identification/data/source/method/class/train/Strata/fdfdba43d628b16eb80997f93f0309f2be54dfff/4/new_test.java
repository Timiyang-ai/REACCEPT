  @Test
  public void test_presentValueSensitivitySabrParameter() {
    testPresentValueSensitivitySabrParameter(
        COUPON_SELL, CAPLET_SELL, FLOORLET_SELL, RATES_PROVIDER, VOLATILITIES);
    testPresentValueSensitivitySabrParameter(
        COUPON, CAPLET_NEGATIVE, FLOORLET_NEGATIVE, RATES_PROVIDER, VOLATILITIES_SHIFT);
    testPresentValueSensitivitySabrParameter(
        COUPON_SELL, CAPLET_SELL, FLOORLET_SELL, RATES_PROVIDER_ON_FIX, VOLATILITIES_ON_FIX);
  }