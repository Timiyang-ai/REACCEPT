static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      CalculationMarketData suppliedData,
      MarketDataConfig marketDataConfig,
      Map<Class<? extends MarketDataId<?>>, MarketDataFunction<?, ?>> functions) {

    DependencyTreeBuilder treeBuilder = DependencyTreeBuilder.of(suppliedData, requirements, marketDataConfig, functions);
    return MarketDataNode.root(treeBuilder.dependencyNodes());
  }