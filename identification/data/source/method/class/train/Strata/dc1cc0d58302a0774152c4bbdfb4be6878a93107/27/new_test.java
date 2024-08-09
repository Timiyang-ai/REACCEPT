  @Test
  public void test_presentValueSensitivity() {
    IborIndexRates mockIbor = mock(IborIndexRates.class);
    SimpleRatesProvider prov = new SimpleRatesProvider();
    prov.setIborRates(mockIbor);

    PointSensitivities sensiPrice = PRICER_PRODUCT.priceSensitivity(FUTURE, prov);
    PointSensitivities sensiPresentValueExpected = sensiPrice.multipliedBy(
        FUTURE.getNotional() * FUTURE.getAccrualFactor() * FUTURE_TRADE.getQuantity());
    PointSensitivities sensiPresentValueComputed = PRICER_TRADE.presentValueSensitivity(FUTURE_TRADE, prov);
    assertThat(sensiPresentValueComputed.equalWithTolerance(sensiPresentValueExpected, TOLERANCE_PV_DELTA)).isTrue();
  }