public final OrderedSetMultimap<Attribute, Dependency> dependentNodeMap(
      TargetAndConfiguration node,
      BuildConfiguration hostConfig,
      @Nullable Aspect aspect,
      ImmutableMap<Label, ConfigMatchingProvider> configConditions)
      throws EvalException, InvalidConfigurationException, InterruptedException,
             InconsistentAspectOrderException {
    NestedSetBuilder<Label> rootCauses = NestedSetBuilder.<Label>stableOrder();
    OrderedSetMultimap<Attribute, Dependency> outgoingEdges = dependentNodeMap(
        node, hostConfig,
        aspect != null ? ImmutableList.of(aspect) : ImmutableList.<Aspect>of(),
        configConditions, rootCauses);
    if (!rootCauses.isEmpty()) {
      throw new IllegalStateException(rootCauses.build().iterator().next().toString());
    }
    return outgoingEdges;
  }