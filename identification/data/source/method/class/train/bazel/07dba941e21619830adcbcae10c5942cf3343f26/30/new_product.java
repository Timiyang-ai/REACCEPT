public final OrderedSetMultimap<Attribute, Dependency> dependentNodeMap(
      TargetAndConfiguration node,
      BuildConfiguration hostConfig,
      @Nullable Aspect aspect,
      ImmutableMap<Label, ConfigMatchingProvider> configConditions,
      ImmutableSet<Label> toolchainLabels,
      @Nullable RuleTransitionFactory trimmingTransitionFactory)
      throws EvalException, InterruptedException, InconsistentAspectOrderException {
    NestedSetBuilder<Cause> rootCauses = NestedSetBuilder.stableOrder();
    OrderedSetMultimap<Attribute, Dependency> outgoingEdges =
        dependentNodeMap(
            node,
            hostConfig,
            aspect != null ? ImmutableList.of(aspect) : ImmutableList.<Aspect>of(),
            configConditions,
            toolchainLabels,
            rootCauses,
            trimmingTransitionFactory);
    if (!rootCauses.isEmpty()) {
      throw new IllegalStateException(rootCauses.build().iterator().next().toString());
    }
    return outgoingEdges;
  }