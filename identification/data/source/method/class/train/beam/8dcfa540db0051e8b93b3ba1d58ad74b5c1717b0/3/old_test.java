  @Test
  public void registerPCollection() throws IOException {
    PCollection<Long> pCollection = pipeline.apply(GenerateSequence.from(0)).setName("foo");
    String id = components.registerPCollection(pCollection);
    assertThat(id, equalTo("foo"));
    components.toComponents().getPcollectionsOrThrow(id);
  }