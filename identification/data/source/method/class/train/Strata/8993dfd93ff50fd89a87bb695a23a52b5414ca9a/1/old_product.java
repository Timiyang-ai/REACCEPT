Pair<MarketDataNode, MarketDataRequirements> withLeavesRemoved() {
    ImmutableList.Builder<MarketDataNode> childNodesBuilder = ImmutableList.builder();
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    for (MarketDataNode child : children) {
      if (child.isLeaf()) {
        switch (child.dataType) {
          case SINGLE_VALUE:
            requirementsBuilder.addValues(child.id);
            break;
          case TIME_SERIES:
            requirementsBuilder.addTimeSeries(((ObservableId) child.id));
            break;
        }
      } else {
        Pair<MarketDataNode, MarketDataRequirements> childResult = child.withLeavesRemoved();
        childNodesBuilder.add(childResult.getFirst());
        requirementsBuilder.addRequirements(childResult.getSecond());
      }
    }
    MarketDataNode node = new MarketDataNode(id, dataType, childNodesBuilder.build());
    MarketDataRequirements requirements = requirementsBuilder.build();

    return Pair.of(node, requirements);
  }