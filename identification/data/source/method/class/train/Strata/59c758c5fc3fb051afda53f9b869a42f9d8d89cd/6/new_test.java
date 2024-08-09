  @Test
  public void test_presentValueSensitivity() {
    PointSensitivities ptsTrade = PRICER_TRADE.presentValueSensitivity(RDEPOSIT_TRADE, IMM_PROV);
    PointSensitivities ptsProduct = PRICER_PRODUCT.presentValueSensitivity(RDEPOSIT_PRODUCT, IMM_PROV);
    assertThat(ptsTrade.equalWithTolerance(ptsProduct, TOLERANCE_PV_DELTA)).isTrue();
  }