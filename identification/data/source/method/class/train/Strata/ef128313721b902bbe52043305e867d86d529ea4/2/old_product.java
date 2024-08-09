public static <T> ScenarioPerturbation<T> none() {
    // TODO Does this need to be a bean so it can be serialized?
    return new ScenarioPerturbation<T>() {
      @Override
      public MarketDataBox<T> applyTo(MarketDataBox<T> marketData) {
        return marketData;
      }

      @Override
      public int getScenarioCount() {
        // A box with one scenario can be used for any number of scenarios
        return 1;
      }
    };
  }