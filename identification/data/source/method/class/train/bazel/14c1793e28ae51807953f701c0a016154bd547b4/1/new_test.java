  @Test
  public void getTransitiveSources_Good() throws Exception {
    NestedSet<Artifact> sources = NestedSetBuilder.create(Order.COMPILE_ORDER, dummyArtifact);
    StructImpl info =
        makeStruct(
            ImmutableMap.of(PyStructUtils.TRANSITIVE_SOURCES, Depset.of(Artifact.TYPE, sources)));
    assertThat(PyStructUtils.getTransitiveSources(info)).isSameInstanceAs(sources);
  }