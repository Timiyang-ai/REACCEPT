  @Test
  public void getTransitiveSources_ModernProvider() throws Exception {
    declareTargetWithImplementation( //
        TRANSITIVE_SOURCES_SETUP_CODE, //
        "return [modern_info]");
    assertThat(PyProviderUtils.getTransitiveSources(getTarget()))
        .containsExactly(getBinArtifact("a.py", getTarget()));
  }