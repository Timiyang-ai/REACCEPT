public static boolean isValid(String keyStr) {
    for (PropertyKey key : PropertyKey.values()) {
      if (key.toString().equals(keyStr)) {
        return true;
      }
    }
    return false;
  }