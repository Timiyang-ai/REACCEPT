public PropertySet combinedWith(PropertySet other) {
    ArgChecker.notNull(other, "other");
    if (other.isEmpty()) {
      return this;
    }
    if (isEmpty()) {
      return other;
    }
    // cannot use ArrayListMultiMap as it does not retain the order of the keys
    // whereas ImmutableListMultimap does retain the order of the keys
    ImmutableListMultimap.Builder<String, String> map = ImmutableListMultimap.builder();
    map.putAll(this.keyValueMap);
    for (String key : other.keyValueMap.keySet()) {
      if (!this.contains(key)) {
        map.putAll(key, other.valueList(key));
      }
    }
    return new PropertySet(map.build());
  }