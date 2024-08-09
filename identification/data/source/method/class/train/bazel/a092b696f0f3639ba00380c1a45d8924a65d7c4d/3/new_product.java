public static boolean getUsesSharedLibraries(TransitiveInfoCollection target)
      throws EvalException {
    if (hasModernProvider(target)) {
      return getModernProvider(target).getUsesSharedLibraries();
    } else if (hasLegacyProvider(target)) {
      return PyStructUtils.getUsesSharedLibraries(getLegacyProvider(target));
    } else {
      NestedSet<Artifact> files = target.getProvider(FileProvider.class).getFilesToBuild();
      return FileType.contains(files, CppFileTypes.SHARED_LIBRARY);
    }
  }