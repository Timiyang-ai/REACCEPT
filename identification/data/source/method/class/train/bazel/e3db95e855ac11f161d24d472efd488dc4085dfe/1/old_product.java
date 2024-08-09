public Fingerprint addNullableString(@Nullable String input) {
    if (input == null) {
      addInt(-1);
    } else {
      addString(input);
    }
    return this;
  }