  @Test
  public void test_parSpread_beforeStart() {
    double parSpread = PRICER.parSpread(SWAP_PRODUCT, PROVIDER);
    ResolvedFxSwap product = ResolvedFxSwap.ofForwardPoints(
        CurrencyAmount.of(USD, NOMINAL_USD), KRW, FX_RATE, FX_FWD_POINTS + parSpread, PAYMENT_DATE_NEAR, PAYMENT_DATE_FAR);
    MultiCurrencyAmount pv = PRICER.presentValue(product, PROVIDER);
    assertThat(pv.convertedTo(USD, PROVIDER).getAmount()).isCloseTo(0d, offset(NOMINAL_USD * TOL));
  }