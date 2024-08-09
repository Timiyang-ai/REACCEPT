public final OrderedSetMultimap<DependencyKind, Dependency> dependentNodeMap(
      TargetAndConfiguration node,
      BuildConfiguration hostConfig,
      Iterable<Aspect> aspects,
      ImmutableMap<Label, ConfigMatchingProvider> configConditions,
      ImmutableSet<Label> toolchainLabels,
      NestedSetBuilder<Cause> rootCauses,
      @Nullable TransitionFactory<Rule> trimmingTransitionFactory)
      throws EvalException, InterruptedException, InconsistentAspectOrderException {
    Target target = node.getTarget();
    BuildConfiguration config = node.getConfiguration();
    OrderedSetMultimap<DependencyKind, Label> outgoingLabels = OrderedSetMultimap.create();

    if (target instanceof OutputFile) {
      Preconditions.checkNotNull(config);
      visitTargetVisibility(node, outgoingLabels);
      Rule rule = ((OutputFile) target).getGeneratingRule();
      outgoingLabels.put(OUTPUT_FILE_RULE_DEPENDENCY, rule.getLabel());
    } else if (target instanceof InputFile) {
      visitTargetVisibility(node, outgoingLabels);
    } else if (target instanceof EnvironmentGroup) {
      visitTargetVisibility(node, outgoingLabels);
    } else if (target instanceof Rule) {
      visitRule(node, hostConfig, aspects, configConditions, toolchainLabels, outgoingLabels);
    } else if (target instanceof PackageGroup) {
      outgoingLabels.putAll(VISIBILITY_DEPENDENCY, ((PackageGroup) target).getIncludes());
    } else {
      throw new IllegalStateException(target.getLabel().toString());
    }

    Rule fromRule = target instanceof Rule ? (Rule) target : null;
    ConfiguredAttributeMapper attributeMap =
        fromRule == null ? null : ConfiguredAttributeMapper.of(fromRule, configConditions);

    Map<Label, Target> targetMap = getTargets(outgoingLabels, target, rootCauses);
    if (targetMap == null) {
      // Dependencies could not be resolved. Try again when they are loaded by Skyframe.
      return OrderedSetMultimap.create();
    }

    OrderedSetMultimap<DependencyKind, PartiallyResolvedDependency> partiallyResolvedDeps =
        partiallyResolveDependencies(outgoingLabels, fromRule, attributeMap, aspects);

    OrderedSetMultimap<DependencyKind, Dependency> outgoingEdges =
        fullyResolveDependencies(
            partiallyResolvedDeps, targetMap, node.getConfiguration(), trimmingTransitionFactory);

    return outgoingEdges;
  }