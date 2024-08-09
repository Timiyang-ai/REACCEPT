  @Test
  public void getHasPy3OnlySources_ModernProvider() throws Exception {
    declareTargetWithImplementation( //
        HAS_PY3_ONLY_SOURCES_SETUP_CODE, //
        "return [modern_info]");
    assertThat(PyProviderUtils.getHasPy3OnlySources(getTarget())).isFalse();
  }