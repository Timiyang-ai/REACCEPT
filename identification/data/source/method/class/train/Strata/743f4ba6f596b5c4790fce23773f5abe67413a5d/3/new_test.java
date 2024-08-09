  @Test
  public void test_presentValue() {
    ScenarioMarketData md = SwaptionTradeCalculationFunctionTest.marketData();
    RatesProvider provider = RATES_LOOKUP.marketDataView(md.scenario(0)).ratesProvider();
    VolatilitySwaptionTradePricer pricer = VolatilitySwaptionTradePricer.DEFAULT;
    CurrencyAmount expectedPv = pricer.presentValue(RTRADE, provider, VOLS);
    MultiCurrencyAmount expectedCurrencyExposure = pricer.currencyExposure(RTRADE, provider, VOLS);
    CurrencyAmount expectedCurrentCash = pricer.currentCash(RTRADE, provider.getValuationDate());

    assertThat(SwaptionTradeCalculations.DEFAULT.presentValue(RTRADE, RATES_LOOKUP, SWAPTION_LOOKUP, md))
        .isEqualTo(CurrencyScenarioArray.of(ImmutableList.of(expectedPv)));
    assertThat(SwaptionTradeCalculations.DEFAULT.currencyExposure(RTRADE, RATES_LOOKUP, SWAPTION_LOOKUP, md))
        .isEqualTo(MultiCurrencyScenarioArray.of(ImmutableList.of(expectedCurrencyExposure)));
    assertThat(SwaptionTradeCalculations.DEFAULT.currentCash(RTRADE, RATES_LOOKUP, SWAPTION_LOOKUP, md))
        .isEqualTo(CurrencyScenarioArray.of(ImmutableList.of(expectedCurrentCash)));
  }