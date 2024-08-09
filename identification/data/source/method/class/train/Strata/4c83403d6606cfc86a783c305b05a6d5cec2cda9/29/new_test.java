  @Test
  public void test_forwardFxRateSpotSensitivity() {
    double computed = PRICER.forwardFxRateSpotSensitivity(FWD, PROVIDER);
    double df1 = PROVIDER.discountFactor(USD, PAYMENT_DATE);
    double df2 = PROVIDER.discountFactor(KRW, PAYMENT_DATE);
    assertThat(computed).isEqualTo(df1 / df2);
  }