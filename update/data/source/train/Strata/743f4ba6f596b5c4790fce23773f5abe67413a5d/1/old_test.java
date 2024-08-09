@Test
  public void test_presentValue() {
    ScenarioMarketData md = DsfTradeCalculationFunctionTest.marketData();
    RatesProvider provider = RATES_LOOKUP.marketDataView(md.scenario(0)).ratesProvider();
    DiscountingDsfTradePricer pricer = DiscountingDsfTradePricer.DEFAULT;
    CurrencyAmount expectedPv = pricer.presentValue(RTRADE, provider, REF_PRICE);
    MultiCurrencyAmount expectedCurrencyExposure = pricer.currencyExposure(RTRADE, provider, REF_PRICE);

    AssertJUnit.assertEquals(
        DsfTradeCalculations.DEFAULT.presentValue(RTRADE, RATES_LOOKUP, md),
        CurrencyValuesArray.of(ImmutableList.of(expectedPv)));
    AssertJUnit.assertEquals(
        DsfTradeCalculations.DEFAULT.currencyExposure(RTRADE, RATES_LOOKUP, md),
        MultiCurrencyValuesArray.of(ImmutableList.of(expectedCurrencyExposure)));
  }