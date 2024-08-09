public static @Nonnull List<MediaType> parse(@Nullable String value) {
    if (value == null || value.length() == 0) {
      return Collections.emptyList();
    }
    List<MediaType> result = new ArrayList<>(3);
    int typeStart = 0;
    int len = value.length();
    for (int i = 0; i < len; i++) {
      char ch = value.charAt(i);
      if (ch == ',') {
        result.add(valueOf(value.substring(typeStart, i).trim()));
        typeStart = i + 1;
      }
    }
    if (typeStart == 0) {
      result.add(valueOf(value));
    } else if (typeStart < len) {
      result.add(valueOf(value.substring(typeStart, len).trim()));
    }
    return result;
  }