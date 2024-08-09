public boolean matches(final MediaType candidate) {
      return doFirst(ImmutableSortedSet.of(candidate)) != null;
    }