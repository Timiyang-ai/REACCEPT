public static ResolvedToolchainContext load(
      UnloadedToolchainContext unloadedToolchainContext,
      String targetDescription,
      Iterable<ConfiguredTargetAndData> toolchainTargets)
      throws ToolchainException {

    ResolvedToolchainContext.Builder toolchainContext =
        new AutoValue_ResolvedToolchainContext.Builder()
            .setTargetDescription(targetDescription)
            .setExecutionPlatform(unloadedToolchainContext.executionPlatform())
            .setTargetPlatform(unloadedToolchainContext.targetPlatform())
            .setRequiredToolchainTypes(unloadedToolchainContext.requiredToolchainTypes())
            .setResolvedToolchainLabels(unloadedToolchainContext.resolvedToolchainLabels())
            .setRequestedToolchainTypeLabels(
                unloadedToolchainContext.requestedLabelToToolchainType());

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

    return toolchainContext
        .setToolchains(toolchains.build())
        .setTemplateVariableProviders(templateVariableProviders.build())
        .build();
  }