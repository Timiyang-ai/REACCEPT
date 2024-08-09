public boolean isSet(PropertyKey key) {
    if (mUserProps.containsKey(key)) {
      return mUserProps.get(key).isPresent();
    }
    // In case key is not the reference to the original key
    return PropertyKey.fromString(key.toString()).getDefaultValue() != null;
  }