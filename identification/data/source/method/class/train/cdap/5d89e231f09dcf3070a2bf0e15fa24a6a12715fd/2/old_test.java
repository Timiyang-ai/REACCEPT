  private List<? extends MetadataMutation> batch(MetadataMutation ... mutations) {
    return ImmutableList.copyOf(mutations);
  }