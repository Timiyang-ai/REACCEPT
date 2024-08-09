  @Test
  public void test_parSpreadSensitivity() {
    IborIndexRates mockIbor = mock(IborIndexRates.class);
    SimpleRatesProvider prov = new SimpleRatesProvider();
    prov.setIborRates(mockIbor);

    PointSensitivities sensiExpected = PRICER_PRODUCT.priceSensitivity(FUTURE, prov);
    PointSensitivities sensiComputed = PRICER_TRADE.parSpreadSensitivity(FUTURE_TRADE, prov);
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, TOLERANCE_PRICE_DELTA)).isTrue();
  }