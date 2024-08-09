private static TypeElement resolveType(Elements elements, String className, StringBuilder sb,
      final int index) {

    // We assume '$' should be converted to '.'. So we search for classes with dots first.
    sb.setCharAt(index, '.');
    int nextIndex = nextDollar(className, sb, index + 1);
    TypeElement type = nextIndex == -1
        ? getTypeElement(elements, sb)
        : resolveType(elements, className, sb, nextIndex);
    if (type != null) {
      return type;
    }

    // if not found, change back to dollar and search.
    sb.setCharAt(index, '$');
    nextIndex = nextDollar(className, sb, index + 1);
    return nextIndex == -1
        ? getTypeElement(elements, sb)
        : resolveType(elements, className, sb, nextIndex);
  }