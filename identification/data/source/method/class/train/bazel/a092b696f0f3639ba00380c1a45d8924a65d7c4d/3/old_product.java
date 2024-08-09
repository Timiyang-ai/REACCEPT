public static boolean getUsesSharedLibraries(TransitiveInfoCollection target)
      throws EvalException {
    if (hasProvider(target)) {
      return PyStructUtils.getUsesSharedLibraries(getProvider(target));
    } else {
      NestedSet<Artifact> files = target.getProvider(FileProvider.class).getFilesToBuild();
      return FileType.contains(files, CppFileTypes.SHARED_LIBRARY);
    }
  }