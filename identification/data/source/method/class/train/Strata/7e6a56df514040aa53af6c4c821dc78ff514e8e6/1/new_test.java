  @Test
  public void test_marketDataView() {
    LocalDate valDate = LocalDate.of(2015, 6, 30);
    ScenarioMarketData md = new TestMarketDataMap(valDate, ImmutableMap.of(), ImmutableMap.of());
    CreditRatesScenarioMarketData multiScenario = LOOKUP_WITH_SOURCE.marketDataView(md);
    assertThat(multiScenario.getLookup()).isEqualTo(LOOKUP_WITH_SOURCE);
    assertThat(multiScenario.getMarketData()).isEqualTo(md);
    assertThat(multiScenario.getScenarioCount()).isEqualTo(1);
    CreditRatesMarketData scenario = multiScenario.scenario(0);
    assertThat(scenario.getLookup()).isEqualTo(LOOKUP_WITH_SOURCE);
    assertThat(scenario.getMarketData()).isEqualTo(md.scenario(0));
    assertThat(scenario.getValuationDate()).isEqualTo(valDate);
  }