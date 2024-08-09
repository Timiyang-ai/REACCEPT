@Nonnull default Optional<String> toOptional() {
    try {
      return Optional.of(value());
    } catch (MissingValueException x) {
      return Optional.empty();
    }
  }