  @Test
  public void test_parRate_ISDA() {
    ResolvedFra fraExp = RFRA;
    SimpleRatesProvider prov = createProvider(fraExp);

    DiscountingFraProductPricer test = DiscountingFraProductPricer.DEFAULT;
    double parRate = test.parRate(fraExp, prov);
    assertThat(parRate).isEqualTo(FORWARD_RATE);
    ResolvedFra fra = createNewFra(FRA, parRate);
    CurrencyAmount pv = test.presentValue(fra, prov);
    assertThat(pv.getAmount()).isCloseTo(0.0, offset(TOLERANCE));

    // test via FraTrade
    DiscountingFraTradePricer testTrade = new DiscountingFraTradePricer(test);
    assertThat(testTrade.parRate(RFRA_TRADE, prov)).isEqualTo(test.parRate(RFRA, prov));
  }