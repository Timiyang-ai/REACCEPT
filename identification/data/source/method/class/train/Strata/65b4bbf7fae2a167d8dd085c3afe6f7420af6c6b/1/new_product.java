static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      ScenarioMarketData suppliedData,
      MarketDataConfig marketDataConfig,
      Map<Class<? extends MarketDataId<?>>, MarketDataFunction<?, ?>> functions) {

    DependencyTreeBuilder treeBuilder = DependencyTreeBuilder.of(suppliedData, requirements, marketDataConfig, functions);
    return MarketDataNode.root(treeBuilder.dependencyNodes());
  }