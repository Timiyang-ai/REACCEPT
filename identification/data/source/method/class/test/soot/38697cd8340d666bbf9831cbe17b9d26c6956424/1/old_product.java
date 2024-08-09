public static String getPackageName(String fullyQualifiedClassName) {
    if (fullyQualifiedClassName == null || fullyQualifiedClassName.isEmpty()) {
      return "";
    }

    int idx = fullyQualifiedClassName.lastIndexOf('.');
    return idx >= 0 ? fullyQualifiedClassName.substring(0, idx + 1) : "";
  }