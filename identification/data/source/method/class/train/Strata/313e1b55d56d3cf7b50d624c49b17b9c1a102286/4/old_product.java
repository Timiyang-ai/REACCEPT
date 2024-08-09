public ImmutableScenarioMarketDataBuilder addScenarioValueMap(
      Map<? extends MarketDataId<?>, ? extends ScenarioMarketDataValue<?>> values) {

    ArgChecker.notNull(values, "values");
    for (Entry<? extends MarketDataId<?>, ? extends ScenarioMarketDataValue<?>> entry : values.entrySet()) {
      MarketDataId<?> id = entry.getKey();
      ScenarioMarketDataValue<?> value = entry.getValue();
      MarketDataBox<?> box = MarketDataBox.ofScenarioValue(value);
      checkBoxType(id, box);
      checkAndUpdateScenarioCount(box);
      this.values.put(id, box);
    }
    return this;
  }