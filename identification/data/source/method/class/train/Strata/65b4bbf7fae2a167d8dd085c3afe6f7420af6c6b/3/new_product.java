static MarketDataNode buildDependencyTree(
      MarketDataRequirements requirements,
      BaseMarketData suppliedData,
      Map<Class<? extends MarketDataId<?>>, MarketDataBuilder<?, ?>> builders) {

    DependencyTreeBuilder treeBuilder = DependencyTreeBuilder.of(suppliedData, requirements, builders);
    return MarketDataNode.root(treeBuilder.childNodes());
  }