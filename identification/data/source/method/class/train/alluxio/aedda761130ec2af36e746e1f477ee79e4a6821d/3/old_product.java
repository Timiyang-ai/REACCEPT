public static PropertyKey fromString(String string) {
    for (PropertyKey key : PropertyKey.values()) {
      if (key.toString().equals(string)) {
        return key;
      }
    }
    throw new IllegalArgumentException("Invalid property key " + string);
  }