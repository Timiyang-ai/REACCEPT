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

    OrderedSetMultimap<Attribute, Dependency> outgoingEdges = OrderedSetMultimap.create();

    List<Label> dependencyLabels =
        outgoingLabels.entries().stream()
            // Toolchains are resolved separately, so we don't need to depend on their packages.
            // It doesn't cause diminished functionality (after all, we depend on a package that
            // must have been loaded), but this makse the error message reporting a missing
            // toolchain a bit better.
            .filter(e -> e.getKey() != TOOLCHAIN_DEPENDENCY)
            .map(e -> e.getValue())
            .distinct()
            .collect(Collectors.toList());

    Map<Label, Target> targetMap = getTargets(dependencyLabels, target, rootCauses);
    if (targetMap == null) {
      // Dependencies could not be resolved. Try again when they are loaded by Skyframe.
      return outgoingEdges;
    }

    Rule fromRule = target instanceof Rule ? (Rule) target : null;
    ConfiguredAttributeMapper attributeMap =
        fromRule == null ? null : ConfiguredAttributeMapper.of(fromRule, configConditions);

    for (Map.Entry<DependencyKind, Label> entry : outgoingLabels.entries()) {
      Label toLabel = entry.getValue();

      if (entry.getKey() == TOOLCHAIN_DEPENDENCY) {
        // This dependency is a toolchain. Its package has not been loaded and therefore we can't
        // determine which aspects and which rule configuration transition we should use, so just
        // use sensible defaults. Not depending on their package makes the error message reporting
        // a missing toolchain a bit better.
        // TODO(lberki): This special-casing is weird. Find a better way to depend on toolchains.
        Attribute toolchainsAttribute =
            attributeMap.getAttributeDefinition(PlatformSemantics.RESOLVED_TOOLCHAINS_ATTR);
        outgoingEdges.put(
            toolchainsAttribute,
            Dependency.withTransitionAndAspects(
                toLabel,
                // TODO(jcater): Replace this with a proper transition for the execution platform.
                HostTransition.INSTANCE,
                AspectCollection.EMPTY));
        continue;
      }

      Target toTarget = targetMap.get(toLabel);

      if (toTarget == null) {
        // Dependency pointing to non-existent target. This error was reported above, so we can just
        // ignore this dependency. Toolchain dependencies always have toTarget == null since we do
        // not depend on their package.
        continue;
      }

      if (entry.getKey() == VISIBILITY_DEPENDENCY) {
        if (toTarget instanceof PackageGroup) {
          outgoingEdges.put(null, Dependency.withNullConfiguration(toLabel));
        } else {
          // Note that this error could also be caught in
          // AbstractConfiguredTarget.convertVisibility(), but we have an
          // opportunity here to avoid dependency cycles that result from
          // the visibility attribute of a rule referring to a rule that
          // depends on it (instead of its package)
          invalidPackageGroupReferenceHook(node, toLabel);
        }

        continue;
      }

      if (entry.getKey() == OUTPUT_FILE_RULE_DEPENDENCY) {
        outgoingEdges.put(
            null,
            Dependency.withTransitionAndAspects(
                toLabel, NoTransition.INSTANCE, AspectCollection.EMPTY));
        continue;
      }

      Attribute attribute = entry.getKey().getAttribute();
      AspectClass ownerAspect = entry.getKey().getOwningAspect();

      ConfigurationTransition attributeTransition =
          attribute.hasSplitConfigurationTransition()
              ? attribute.getSplitTransition(attributeMap)
              : attribute.getConfigurationTransition();

      ConfigurationTransition transition =
          TransitionResolver.evaluateTransition(
              node.getConfiguration(), attributeTransition, toTarget, trimmingTransitionFactory);
      AspectCollection requiredAspects =
          requiredAspects(fromRule, aspects, attribute, ownerAspect, toTarget);
      outgoingEdges.put(
          attribute,
          transition == NullTransition.INSTANCE
              ? Dependency.withNullConfiguration(toLabel)
              : Dependency.withTransitionAndAspects(entry.getValue(), transition, requiredAspects));
    }

    return outgoingEdges;
  }