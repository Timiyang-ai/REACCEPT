public static ImmutableMap<UnflavoredBuildTargetView, MergedTargetNode> group(
      Collection<TargetNode<?>> targetNodes) {
    Map<UnflavoredBuildTargetView, List<TargetNode<?>>> collect =
        targetNodes.stream()
            .collect(Collectors.groupingBy(t -> t.getBuildTarget().getUnflavoredBuildTarget()));
    ImmutableMap.Builder<UnflavoredBuildTargetView, MergedTargetNode> builder =
        ImmutableMap.builder();
    for (Map.Entry<UnflavoredBuildTargetView, List<TargetNode<?>>> entry : collect.entrySet()) {
      // Sort nodes to make everything deterministic
      ImmutableList<TargetNode<?>> nodes =
          entry.getValue().stream().sorted().collect(ImmutableList.toImmutableList());
      builder.put(entry.getKey(), new MergedTargetNode(entry.getKey(), nodes));
    }
    return builder.build();
  }