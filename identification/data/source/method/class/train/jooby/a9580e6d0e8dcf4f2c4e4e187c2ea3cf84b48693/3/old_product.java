public boolean matches(final @Nonnull List<MediaType> candidates) {
      return filter(candidates).size() > 0;
    }