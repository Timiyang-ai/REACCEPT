public PropertySet combinedWith(PropertySet other) {
    ArgChecker.notNull(other, "other");
    if (other.isEmpty()) {
      return this;
    }
    if (isEmpty()) {
      return other;
    }
    ListMultimap<String, String> map = ArrayListMultimap.create(keyValueMap);
    for (String key : other.asMap().keySet()) {
      map.removeAll(key);
      map.putAll(key, other.getValueList(key));
    }
    return new PropertySet(ImmutableListMultimap.copyOf(map));
  }