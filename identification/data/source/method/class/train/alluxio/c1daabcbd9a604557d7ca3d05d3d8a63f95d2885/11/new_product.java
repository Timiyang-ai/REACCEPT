public boolean isSet(PropertyKey key) {
    if (mUserProps.containsKey(key)) {
      Optional<String> val = mUserProps.get(key);
      return val.isPresent();
    }
    // In case key is not the reference to the original key
    return PropertyKey.fromString(key.toString()).getDefaultValue() != null;
  }