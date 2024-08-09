public static ResolvedToolchainContext load(
      ImmutableMap<RepositoryName, RepositoryName> repoMapping,
      UnloadedToolchainContext unloadedToolchainContext,
      String targetDescription,
      Iterable<ConfiguredTargetAndData> toolchainTargets)
      throws ToolchainException {

    ImmutableMap.Builder<ToolchainTypeInfo, ToolchainInfo> toolchains =
        new ImmutableMap.Builder<>();
    ImmutableList.Builder<TemplateVariableInfo> templateVariableProviders =
        new ImmutableList.Builder<>();
    for (ConfiguredTargetAndData target : toolchainTargets) {
      Label discoveredLabel;
      // Aliases are in toolchainTypeToResolved by the original alias label, not via the final
      // target's label.
      if (target.getConfiguredTarget() instanceof AliasConfiguredTarget) {
        discoveredLabel = ((AliasConfiguredTarget) target.getConfiguredTarget()).getOriginalLabel();
      } else {
        discoveredLabel = target.getConfiguredTarget().getLabel();
      }
      ToolchainTypeInfo toolchainType =
          unloadedToolchainContext.toolchainTypeToResolved().inverse().get(discoveredLabel);
      ToolchainInfo toolchainInfo = PlatformProviderUtils.toolchain(target.getConfiguredTarget());

      // If the toolchainType hadn't been resolved to an actual target, resolution would have
      // failed with an error much earlier. However, the target might still not be an actual
      // toolchain.
      if (toolchainType != null) {
        if (toolchainInfo != null) {
          toolchains.put(toolchainType, toolchainInfo);
        } else {
          throw new TargetNotToolchainException(toolchainType, discoveredLabel);
        }
      }

      // Find any template variables present for this toolchain.
      TemplateVariableInfo templateVariableInfo =
          target.getConfiguredTarget().get(TemplateVariableInfo.PROVIDER);
      if (templateVariableInfo != null) {
        templateVariableProviders.add(templateVariableInfo);
      }
    }

    return new AutoValue_ResolvedToolchainContext(
        // super:
        unloadedToolchainContext.executionPlatform(),
        unloadedToolchainContext.targetPlatform(),
        unloadedToolchainContext.requiredToolchainTypes(),
        unloadedToolchainContext.resolvedToolchainLabels(),
        // this:
        repoMapping,
        targetDescription,
        unloadedToolchainContext.requestedLabelToToolchainType(),
        toolchains.build(),
        templateVariableProviders.build());
  }