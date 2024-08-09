@VisibleForTesting
  static PackageIdentifier longestPathPrefix(
      PackageIdentifier path, Set<PackageIdentifier> prefixes) {
    for (int i = path.getPackageFragment().segmentCount(); i >= 0; i--) {
      PackageIdentifier prefix = createInRepo(path, path.getPackageFragment().subFragment(0, i));
      if (prefixes.contains(prefix)) {
        return prefix;
      }
    }
    return null;
  }