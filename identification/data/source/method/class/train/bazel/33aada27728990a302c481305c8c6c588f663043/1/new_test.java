  private static PathFragment longestPathPrefix(String path, String... prefixStrs) {
    ImmutableSet.Builder<PackageIdentifier> prefixes = ImmutableSet.builder();
    for (String prefix : prefixStrs) {
      prefixes.add(PackageIdentifier.createInMainRepo(prefix));
    }
    PackageIdentifier longest = SymlinkForest.longestPathPrefix(
        PackageIdentifier.createInMainRepo(path), prefixes.build());
    return longest != null ? longest.getPackageFragment() : null;
  }