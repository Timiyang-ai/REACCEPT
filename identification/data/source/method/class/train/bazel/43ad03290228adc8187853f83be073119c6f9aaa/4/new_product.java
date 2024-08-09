public Fingerprint addNullableBoolean(Boolean input) {
    if (input == null) {
      addBoolean(false);
    } else {
      addBoolean(true);
      addBoolean(input);
    }
    return this;
  }