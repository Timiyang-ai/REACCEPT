@Nonnull default Optional<String> toOptional() {
    try {
      return Optional.of(value());
    } catch (Err.Missing x) {
      return Optional.empty();
    }
  }