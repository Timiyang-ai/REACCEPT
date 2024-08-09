public static NestedSet<Artifact> getTransitiveSources(TransitiveInfoCollection target)
      throws EvalException {
    if (hasModernProvider(target)) {
      return getModernProvider(target).getTransitiveSourcesSet();
    } else if (hasLegacyProvider(target)) {
      return PyStructUtils.getTransitiveSources(getLegacyProvider(target));
    } else {
      NestedSet<Artifact> files = target.getProvider(FileProvider.class).getFilesToBuild();
      return NestedSetBuilder.<Artifact>compileOrder()
          .addAll(FileType.filter(files, PyRuleClasses.PYTHON_SOURCE))
          .build();
    }
  }