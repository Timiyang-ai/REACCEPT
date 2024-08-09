public static MergedTargetGraph merge(DirectedAcyclicGraph<TargetNode<?>> targetGraph) {
    ImmutableMap<UnflavoredBuildTargetView, MergedTargetNode> index =
        MergedTargetNode.group(targetGraph.getNodes());

    MutableDirectedGraph<MergedTargetNode> graph = new MutableDirectedGraph<>();

    for (MergedTargetNode node : index.values()) {
      graph.addNode(node);
    }

    for (Map.Entry<TargetNode<?>, TargetNode<?>> edge : targetGraph.getOutgoingEdges().entries()) {
      TargetNode<?> source = edge.getKey();
      TargetNode<?> sink = edge.getValue();
      MergedTargetNode mergedSource =
          Preconditions.checkNotNull(
              index.get(source.getBuildTarget().getUnflavoredBuildTarget()),
              "node must exist in index: %s",
              source.getBuildTarget().getUnflavoredBuildTarget());
      MergedTargetNode mergedSink =
          Preconditions.checkNotNull(
              index.get(sink.getBuildTarget().getUnflavoredBuildTarget()),
              "node must exist in index: %s",
              sink.getBuildTarget().getUnflavoredBuildTarget());
      graph.addEdge(mergedSource, mergedSink);
    }

    return new MergedTargetGraph(graph, index);
  }