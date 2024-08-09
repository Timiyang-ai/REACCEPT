protected void put(PropertyKey key, String value, Source source) {
    if (!mUserProps.containsKey(key) || source.compareTo(getSource(key)) >= 0) {
      mUserProps.put(key, Optional.ofNullable(value));
      mSources.put(key, source);
    }
  }