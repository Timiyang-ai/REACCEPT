public boolean matches(final MediaType candidate) {
      return doFirst(ImmutableList.of(candidate)).isPresent();
    }