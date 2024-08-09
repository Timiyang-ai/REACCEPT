public boolean matches(final MediaType candidate) {
      requireNonNull(candidate, "A candidate media type is required.");
      return doFirst(ImmutableList.of(candidate)) != null;
    }