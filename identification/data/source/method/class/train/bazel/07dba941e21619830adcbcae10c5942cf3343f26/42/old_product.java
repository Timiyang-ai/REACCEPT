public final ListMultimap<Attribute, Dependency> dependentNodeMap(
      TargetAndConfiguration node, BuildConfiguration hostConfig, AspectDefinition aspect,
      Set<ConfigMatchingProvider> configConditions)
      throws EvalException {
    Target target = node.getTarget();
    BuildConfiguration config = node.getConfiguration();
    ListMultimap<Attribute, Dependency> outgoingEdges = ArrayListMultimap.create();
    if (target instanceof OutputFile) {
      Preconditions.checkNotNull(config);
      visitTargetVisibility(node, outgoingEdges.get(null));
      Rule rule = ((OutputFile) target).getGeneratingRule();
      outgoingEdges.put(null, new Dependency(rule.getLabel(), config));
    } else if (target instanceof InputFile) {
      visitTargetVisibility(node, outgoingEdges.get(null));
    } else if (target instanceof EnvironmentGroup) {
      visitTargetVisibility(node, outgoingEdges.get(null));
    } else if (target instanceof Rule) {
      Preconditions.checkNotNull(config);
      visitTargetVisibility(node, outgoingEdges.get(null));
      Rule rule = (Rule) target;
      ListMultimap<Attribute, LabelAndConfiguration> labelMap =
          resolveAttributes(rule, aspect, config, hostConfig, configConditions);
      visitRule(rule, aspect, labelMap, outgoingEdges);
    } else if (target instanceof PackageGroup) {
      visitPackageGroup(node, (PackageGroup) target, outgoingEdges.get(null));
    } else {
      throw new IllegalStateException(target.getLabel().toString());
    }
    return outgoingEdges;
  }