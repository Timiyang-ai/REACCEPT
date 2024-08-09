public static PropertyKey fromString(String keyStr) {
    ConstantPropertyKey key = ConstantPropertyKey.fromString(keyStr);
    if (key != null) {
      return key;
    }
    return ParameterizedPropertyKey.fromString(keyStr);
  }