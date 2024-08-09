public static String getPackageName(String fullyQualifiedClassName) {
    if (fullyQualifiedClassName == null || fullyQualifiedClassName.isEmpty()) {
      return null;
    }

    int idx = fullyQualifiedClassName.lastIndexOf('.');
    return idx >= 1 ? fullyQualifiedClassName.substring(0, idx) : null;
  }