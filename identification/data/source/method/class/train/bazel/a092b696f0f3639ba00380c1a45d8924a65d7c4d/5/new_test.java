  @Test
  public void getHasPy2OnlySources_ModernProvider() throws Exception {
    declareTargetWithImplementation( //
        HAS_PY2_ONLY_SOURCES_SETUP_CODE, //
        "return [modern_info]");
    assertThat(PyProviderUtils.getHasPy2OnlySources(getTarget())).isFalse();
  }