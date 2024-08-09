@VisibleForTesting
  static PathFragment longestPathPrefix(PathFragment path, Set<PathFragment> prefixes) {
    for (int i = path.segmentCount(); i >= 0; i--) {
      PathFragment prefix = path.subFragment(0, i);
      if (prefixes.contains(prefix)) {
        return prefix;
      }
    }
    return null;
  }