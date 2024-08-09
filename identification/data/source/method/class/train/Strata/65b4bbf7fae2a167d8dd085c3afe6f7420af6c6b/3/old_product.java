static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      BaseMarketData suppliedData,
      Map<Class<? extends MarketDataId<?>>, MarketDataBuilder<?, ?>> builders) {

    return MarketDataNode.root(children(requirements, suppliedData, builders));
  }