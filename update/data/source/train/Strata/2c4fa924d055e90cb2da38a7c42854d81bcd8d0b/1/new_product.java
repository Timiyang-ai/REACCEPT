public PropertySet overrideWith(PropertySet other) {
    ArgChecker.notNull(other, "other");
    return other.combinedWith(this);
  }