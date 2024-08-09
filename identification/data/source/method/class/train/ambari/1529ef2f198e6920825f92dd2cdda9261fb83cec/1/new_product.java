public static boolean isPassword(String category, String propertyName) {
    return isPassword(getConfigurationKey(category, propertyName));
  }