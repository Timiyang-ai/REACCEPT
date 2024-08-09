default <T> Optional<T> toOptional(Class<T> type) {
    return to(Reified.optional(type));
  }