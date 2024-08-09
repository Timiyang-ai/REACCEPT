public final OrderedSetMultimap<Attribute, Dependency> dependentNodeMap(
      TargetAndConfiguration node,
      BuildConfiguration hostConfig,
      Iterable<Aspect> aspects,
      ImmutableMap<Label, ConfigMatchingProvider> configConditions,
      ImmutableSet<Label> toolchainLabels,
      NestedSetBuilder<Cause> rootCauses,
      @Nullable RuleTransitionFactory trimmingTransitionFactory)
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
    OrderedSetMultimap<DependencyKind, PartiallyResolvedDependency> partiallyResolvedDeps =
        OrderedSetMultimap.create();

    Map<Label, Target> targetMap = getTargets(outgoingLabels, target, rootCauses);
    if (targetMap == null) {
      // Dependencies could not be resolved. Try again when they are loaded by Skyframe.
      return OrderedSetMultimap.create();
    }

    for (Map.Entry<DependencyKind, Label> entry : outgoingLabels.entries()) {
      Label toLabel = entry.getValue();

      if (entry.getKey() == TOOLCHAIN_DEPENDENCY) {
        // This dependency is a toolchain. Its package has not been loaded and therefore we can't
        // determine which aspects and which rule configuration transition we should use, so just
        // use sensible defaults. Not depending on their package makes the error message reporting
        // a missing toolchain a bit better.
        // TODO(lberki): This special-casing is weird. Find a better way to depend on toolchains.
        partiallyResolvedDeps.put(
            TOOLCHAIN_DEPENDENCY,
            PartiallyResolvedDependency.of(
                toLabel,
                // TODO(jcater): Replace this with a proper transition for the execution platform.
                HostTransition.INSTANCE,
                ImmutableList.of()));
        continue;
      }

      if (entry.getKey() == VISIBILITY_DEPENDENCY) {
        // Package groups should be analyzed with the null configuration, but we don't know if this
        // is actually a package group. So just use no transition until it's known whether that
        // assumption is correct.
        partiallyResolvedDeps.put(
            VISIBILITY_DEPENDENCY,
            PartiallyResolvedDependency.of(toLabel, NoTransition.INSTANCE, ImmutableList.of()));
        continue;
      }

      if (entry.getKey() == OUTPUT_FILE_RULE_DEPENDENCY) {
        partiallyResolvedDeps.put(
            OUTPUT_FILE_RULE_DEPENDENCY,
            PartiallyResolvedDependency.of(toLabel, NoTransition.INSTANCE, ImmutableList.of()));
        continue;
      }

      Attribute attribute = entry.getKey().getAttribute();
      ImmutableList.Builder<Aspect> propagatingAspects = ImmutableList.builder();
      propagatingAspects.addAll(attribute.getAspects(fromRule));
      collectPropagatingAspects(
          aspects, attribute.getName(), entry.getKey().getOwningAspect(), propagatingAspects);

      ConfigurationTransition attributeTransition =
          attribute.hasSplitConfigurationTransition()
              ? attribute.getSplitTransition(attributeMap)
              : attribute.getConfigurationTransition();
      partiallyResolvedDeps.put(
          entry.getKey(),
          PartiallyResolvedDependency.of(toLabel, attributeTransition, propagatingAspects.build()));
    }

    Set<PartiallyResolvedDependency> illegalVisibilityDeps = new LinkedHashSet<>();
    for (PartiallyResolvedDependency dep : partiallyResolvedDeps.get(VISIBILITY_DEPENDENCY)) {
      Target toTarget = targetMap.get(dep.getLabel());
      if (toTarget == null) {
        // Dependency pointing to non-existent target. This error was reported above, so we can just
        // ignore this dependency.
      }

      if (!(toTarget instanceof PackageGroup)) {
        // Note that this error could also be caught in
        // AbstractConfiguredTarget.convertVisibility(), but we have an
        // opportunity here to avoid dependency cycles that result from
        // the visibility attribute of a rule referring to a rule that
        // depends on it (instead of its package)
        invalidPackageGroupReferenceHook(node, dep.getLabel());
        illegalVisibilityDeps.add(dep);
      }
    }

    for (PartiallyResolvedDependency illegalVisibilityDep : illegalVisibilityDeps) {
      partiallyResolvedDeps.remove(VISIBILITY_DEPENDENCY, illegalVisibilityDep);
    }

    OrderedSetMultimap<Attribute, Dependency> outgoingEdges = OrderedSetMultimap.create();

    for (Map.Entry<DependencyKind, PartiallyResolvedDependency> entry :
        partiallyResolvedDeps.entries()) {
      PartiallyResolvedDependency dep = entry.getValue();

      Target toTarget = targetMap.get(dep.getLabel());
      if (toTarget == null) {
        // Dependency pointing to non-existent target. This error was reported above, so we can just
        // ignore this dependency. Toolchain dependencies always have toTarget == null since we do
        // not depend on their package.
        continue;
      }

      ConfigurationTransition transition =
          TransitionResolver.evaluateTransition(
              node.getConfiguration(), dep.getTransition(), toTarget, trimmingTransitionFactory);

      AspectCollection requiredAspects =
          filterPropagatingAspects(dep.getPropagatingAspects(), toTarget);

      Attribute attribute =
          entry.getKey() == TOOLCHAIN_DEPENDENCY
              ? attributeMap.getAttributeDefinition(PlatformSemantics.RESOLVED_TOOLCHAINS_ATTR)
              : entry.getKey().getAttribute();
      outgoingEdges.put(
          attribute,
          transition == NullTransition.INSTANCE
              ? Dependency.withNullConfiguration(dep.getLabel())
              : Dependency.withTransitionAndAspects(dep.getLabel(), transition, requiredAspects));
    }

    return outgoingEdges;
  }