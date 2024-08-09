public Fingerprint addNullableString(@Nullable String input) {
    if (input == null) {
      addBoolean(false);
    } else {
      addBoolean(true);
      addString(input);
    }
    return this;
  }