public final ListMultimap<Attribute, Dependency> dependentNodeMap(
      TargetAndConfiguration node,
      BuildConfiguration hostConfig,
      Aspect aspect,
      ImmutableMap<Label, ConfigMatchingProvider> configConditions)
      throws EvalException, InterruptedException {
    NestedSetBuilder<Label> rootCauses = NestedSetBuilder.<Label>stableOrder();
    ListMultimap<Attribute, Dependency> outgoingEdges =
        dependentNodeMap(node, hostConfig, aspect, configConditions, rootCauses);
    if (!rootCauses.isEmpty()) {
      throw new IllegalStateException(rootCauses.build().iterator().next().toString());
    }
    return outgoingEdges;
  }