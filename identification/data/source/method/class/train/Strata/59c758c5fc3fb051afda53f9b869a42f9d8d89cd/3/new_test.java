  @Test
  public void test_parSpread() {
    double psTrade = PRICER_TRADE.parSpread(RDEPOSIT_TRADE, IMM_PROV);
    double psProduct = PRICER_PRODUCT.parSpread(RDEPOSIT_PRODUCT, IMM_PROV);
    assertThat(psTrade).isCloseTo(psProduct, offset(TOLERANCE_RATE));
    
  }