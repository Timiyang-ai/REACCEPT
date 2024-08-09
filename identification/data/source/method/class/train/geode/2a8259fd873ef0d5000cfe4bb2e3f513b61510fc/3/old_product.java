public static Class<?> getCallerClass(final int depth) throws ClassNotFoundException {
    return Class.forName(getCallerClassName(depth + 1));
  }