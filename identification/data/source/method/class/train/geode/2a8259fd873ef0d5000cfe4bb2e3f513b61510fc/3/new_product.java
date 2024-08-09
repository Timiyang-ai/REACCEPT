public static Class<?> getCallerClass(final int depth) {
    try {
      return Class.forName(getCallerClassName(depth + 1));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }