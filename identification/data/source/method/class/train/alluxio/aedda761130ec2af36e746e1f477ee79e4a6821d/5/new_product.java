public static PropertyKey fromString(String keyStr) {
    PropertyKey key = KEYS_MAP.get(keyStr);
    if (key == null) {
      throw new IllegalArgumentException("Invalid property key " + keyStr);
    }
    return key;
  }