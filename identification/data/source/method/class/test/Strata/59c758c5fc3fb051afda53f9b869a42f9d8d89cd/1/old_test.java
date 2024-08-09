  @Test
  public void test_parSpreadSensitivity() {
    PointSensitivities ptsTrade = PRICER_TRADE.parSpreadSensitivity(RDEPOSIT_TRADE, IMM_PROV);
    PointSensitivities ptsProduct = PRICER_PRODUCT.parSpreadSensitivity(RDEPOSIT_PRODUCT, IMM_PROV);
    assertThat(ptsTrade.equalWithTolerance(ptsProduct, TOLERANCE_PV_DELTA)).isTrue();
  }