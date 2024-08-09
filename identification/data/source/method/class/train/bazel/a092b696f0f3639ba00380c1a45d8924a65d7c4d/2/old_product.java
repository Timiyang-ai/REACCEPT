public static NestedSet<Artifact> getTransitiveSources(TransitiveInfoCollection target)
      throws EvalException {
    if (hasProvider(target)) {
      return PyStructUtils.getTransitiveSources(getProvider(target));
    } else {
      NestedSet<Artifact> files = target.getProvider(FileProvider.class).getFilesToBuild();
      return NestedSetBuilder.<Artifact>compileOrder()
          .addAll(FileType.filter(files, PyRuleClasses.PYTHON_SOURCE))
          .build();
    }
  }