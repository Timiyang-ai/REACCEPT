static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      BaseMarketData suppliedData,
      Map<Class<? extends MarketDataId<?>>, MarketDataFunction<?, ?>> functions) {

    DependencyTreeBuilder treeBuilder = DependencyTreeBuilder.of(suppliedData, requirements, functions);
    return MarketDataNode.root(treeBuilder.childNodes());
  }