public static boolean contains(Iterator<?> iterator, @Nullable Object element) {
    return any(iterator, equalTo(element));
  }