public Set<PropertyKey> keySet() {
    Set<PropertyKey> keySet = new HashSet<>(PropertyKey.defaultKeys());
    keySet.addAll(mUserProps.keySet());
    return Collections.unmodifiableSet(keySet);
  }