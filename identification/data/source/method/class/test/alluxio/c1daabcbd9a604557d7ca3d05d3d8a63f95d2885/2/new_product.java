public boolean isSet(PropertyKey key) {
    if (isSetByUser(key)) {
      return true;
    }
    // In case key is not the reference to the original key
    return PropertyKey.fromString(key.toString()).getDefaultValue() != null;
  }