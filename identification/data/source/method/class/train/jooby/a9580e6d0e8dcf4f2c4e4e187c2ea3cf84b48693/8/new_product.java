public boolean matches(final List<MediaType> candidates) {
      return filter(candidates).size() > 0;
    }