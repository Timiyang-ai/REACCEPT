public Fingerprint addNullableBoolean(Boolean input) {
    return addInt(input == null ? -1 : (input.booleanValue() ? 1 : 0));
  }