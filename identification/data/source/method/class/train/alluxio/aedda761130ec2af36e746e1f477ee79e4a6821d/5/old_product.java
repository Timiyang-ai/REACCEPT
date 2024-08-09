public static PropertyKey fromString(String keyStr) {
    for (PropertyKey key : PropertyKey.values()) {
      if (key.toString().equals(keyStr)) {
        return key;
      }
    }
    throw new IllegalArgumentException("Invalid property key " + keyStr);
  }