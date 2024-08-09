@Nonnull default <T> Optional<T> toOptional(@Nonnull Class<T> type) {
    return to(Reified.optional(type));
  }