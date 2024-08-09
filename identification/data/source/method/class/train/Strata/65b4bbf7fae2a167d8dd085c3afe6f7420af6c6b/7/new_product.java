static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      BaseMarketData suppliedData,
      MarketDataConfig marketDataConfig,
      Map<Class<? extends MarketDataId<?>>, MarketDataFunction<?, ?>> functions) {

    DependencyTreeBuilder treeBuilder = DependencyTreeBuilder.of(suppliedData, requirements, marketDataConfig, functions);
    return MarketDataNode.root(treeBuilder.childNodes());
  }