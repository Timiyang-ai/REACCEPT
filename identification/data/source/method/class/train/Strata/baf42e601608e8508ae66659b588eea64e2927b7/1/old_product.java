public PropertySet combinedWith(PropertySet other) {
    ArgChecker.notNull(other, "other");
    if (other.isEmpty()) {
      return this;
    }
    if (isEmpty()) {
      return other;
    }
    ListMultimap<String, String> map = ArrayListMultimap.create(keyValueMap);
    for (String key : other.asMultimap().keySet()) {
      map.removeAll(key);
      map.putAll(key, other.valueList(key));
    }
    return new PropertySet(ImmutableListMultimap.copyOf(map));
  }