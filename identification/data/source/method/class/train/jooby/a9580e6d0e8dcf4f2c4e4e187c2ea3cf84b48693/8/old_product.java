public boolean matches(final Iterable<MediaType> candidates) {
      return doFirst(candidates) != null;
    }