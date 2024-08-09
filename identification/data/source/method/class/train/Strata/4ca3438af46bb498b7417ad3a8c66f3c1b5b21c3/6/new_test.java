  @Test
  public void test_parSpread_ISDA() {
    ResolvedFra fraExp = RFRA;
    SimpleRatesProvider prov = createProvider(fraExp);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    double parSpread = test.parSpread(fraExp, prov);
    ResolvedFra fra = createNewFra(FRA, FRA.getFixedRate() + parSpread);
    CurrencyAmount pv = test.presentValue(fra, prov);
    assertThat(pv.getAmount()).isCloseTo(0.0, offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.parSpread(RFRA_TRADE, prov)).isEqualTo(test.parSpread(RFRA, prov));
  }