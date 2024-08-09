public boolean matches(final MediaType candidate) {
      requireNonNull(candidate, "A candidate media type is required.");
      return doFirst(ImmutableSortedSet.of(candidate)) != null;
    }