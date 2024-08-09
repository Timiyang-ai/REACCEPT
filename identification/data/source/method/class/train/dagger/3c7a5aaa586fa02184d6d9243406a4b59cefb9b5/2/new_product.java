@VisibleForTesting static TypeElement resolveType(Elements elements, String className) {
    int index = nextDollar(className, className, 0);
    if (index == -1) {
      return getTypeElement(elements, className);
    }
    // have to test various possibilities of replacing '$' with '.' since '.' in a canonical name
    // of a nested type is replaced with '$' in the binary name.
    StringBuilder sb = new StringBuilder(className);
    return resolveType(elements, className, sb, index);
  }