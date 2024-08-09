public Fingerprint addNullableBoolean(Boolean input) {
    addInt(input == null ? -1 : (input.booleanValue() ? 1 : 0));
    return this;
  }