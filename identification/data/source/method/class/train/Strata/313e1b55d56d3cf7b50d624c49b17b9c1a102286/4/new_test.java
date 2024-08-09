  @Test
  public void test_addScenarioValueMap() {
    FxRateId eurGbpId = FxRateId.of(Currency.EUR, Currency.GBP);
    FxRateId eurUsdId = FxRateId.of(Currency.EUR, Currency.USD);
    FxRateScenarioArray eurGbpRates = FxRateScenarioArray.of(Currency.EUR, Currency.GBP, DoubleArray.of(0.79, 0.8, 0.81));
    FxRateScenarioArray eurUsdRates = FxRateScenarioArray.of(Currency.EUR, Currency.USD, DoubleArray.of(1.09, 1.1, 1.11));
    Map<FxRateId, FxRateScenarioArray> values = ImmutableMap.of(
        eurGbpId, eurGbpRates,
        eurUsdId, eurUsdRates);

    ImmutableScenarioMarketData marketData = ImmutableScenarioMarketData.builder(VAL_DATE)
        .addScenarioValueMap(values)
        .build();
    assertThat(marketData.getScenarioCount()).isEqualTo(3);
    assertThat(marketData.getIds()).containsExactlyInAnyOrder(eurGbpId, eurUsdId);
    assertThat(marketData.getValue(eurGbpId)).isEqualTo(MarketDataBox.ofScenarioValue(eurGbpRates));
    assertThat(marketData.getValue(eurUsdId)).isEqualTo(MarketDataBox.ofScenarioValue(eurUsdRates));
  }