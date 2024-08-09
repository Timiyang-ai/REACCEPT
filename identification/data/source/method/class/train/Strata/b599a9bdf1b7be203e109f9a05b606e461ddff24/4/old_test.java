  @Test
  public void test_priceSensitivity() {
    IborIndexRates mockIbor = mock(IborIndexRates.class);
    SimpleRatesProvider prov = new SimpleRatesProvider();
    prov.setIborRates(mockIbor);

    PointSensitivities sensiExpected =
        PointSensitivities.of(IborRateSensitivity.of(FUTURE.getIborRate().getObservation(), -1d));
    PointSensitivities sensiComputed = PRICER.priceSensitivity(FUTURE, prov);
    assertThat(sensiComputed.equalWithTolerance(sensiExpected, TOLERANCE_PRICE_DELTA)).isTrue();
  }