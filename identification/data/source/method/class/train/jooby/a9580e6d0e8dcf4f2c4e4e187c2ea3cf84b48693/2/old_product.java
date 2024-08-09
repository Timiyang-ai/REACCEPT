public boolean matches(final @Nonnull MediaType candidate) {
      requireNonNull(candidate, "A candidate media type is required.");
      return doFirst(ImmutableList.of(candidate)) != null;
    }