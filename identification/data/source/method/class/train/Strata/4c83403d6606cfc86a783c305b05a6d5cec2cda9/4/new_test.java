  @Test
  public void test_parSpread() {
    double spread = PRICER.parSpread(FWD, PROVIDER);
    ResolvedFxSingle fwdSp =
        ResolvedFxSingle.of(CurrencyAmount.of(USD, NOMINAL_USD), FxRate.of(USD, KRW, FX_RATE + spread), PAYMENT_DATE);
    MultiCurrencyAmount pv = PRICER.presentValue(fwdSp, PROVIDER);
    assertThat(pv.convertedTo(USD, PROVIDER).getAmount()).isCloseTo(0d, offset(NOMINAL_USD * TOL));
  }