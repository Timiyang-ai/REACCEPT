  @Test
  public void getTags_empty() {
    TagMapImpl tags = new TagMapImpl(ImmutableMap.<TagKey, TagValueWithMetadata>of());
    assertThat(tags.getTags()).isEmpty();
  }