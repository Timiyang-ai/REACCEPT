public static String toString(final Object... array) {
    final StringBuilder buffer = new StringBuilder("[");
    int count = 0;

    if (array != null) {
      for (final Object element : array) {
        buffer.append(count++ > 0 ? ", " : StringUtils.EMPTY).append(element);
      }
    }

    buffer.append("]");

    return buffer.toString();
  }