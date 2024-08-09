public ImmutableScenarioMarketDataBuilder addScenarioValueMap(
      Map<? extends MarketDataId<?>, ? extends ScenarioArray<?>> values) {

    ArgChecker.notNull(values, "values");
    for (Entry<? extends MarketDataId<?>, ? extends ScenarioArray<?>> entry : values.entrySet()) {
      MarketDataId<?> id = entry.getKey();
      ScenarioArray<?> value = entry.getValue();
      MarketDataBox<?> box = MarketDataBox.ofScenarioValue(value);
      checkBoxType(id, box);
      checkAndUpdateScenarioCount(box);
      this.values.put(id, box);
    }
    return this;
  }