  @Test
  public void test_marketDataView() {
    ImmutableMap<Currency, CurveId> discounts = ImmutableMap.of(USD, CURVE_ID_DSC);
    ImmutableMap<Index, CurveId> forwards = ImmutableMap.of(USD_LIBOR_3M, CURVE_ID_FWD);
    RatesMarketDataLookup test = RatesMarketDataLookup.of(discounts, forwards);
    LocalDate valDate = date(2015, 6, 30);
    ScenarioMarketData md = new TestMarketDataMap(valDate, ImmutableMap.of(), ImmutableMap.of());
    RatesScenarioMarketData multiScenario = test.marketDataView(md);
    assertThat(multiScenario.getLookup()).isEqualTo(test);
    assertThat(multiScenario.getMarketData()).isEqualTo(md);
    assertThat(multiScenario.getScenarioCount()).isEqualTo(1);
    RatesMarketData scenario = multiScenario.scenario(0);
    assertThat(scenario.getLookup()).isEqualTo(test);
    assertThat(scenario.getMarketData()).isEqualTo(md.scenario(0));
    assertThat(scenario.getValuationDate()).isEqualTo(valDate);
  }