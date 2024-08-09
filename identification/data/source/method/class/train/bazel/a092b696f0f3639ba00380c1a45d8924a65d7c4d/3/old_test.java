  @Test
  public void getUsesSharedLibraries_ModernProvider() throws Exception {
    declareTargetWithImplementation( //
        USES_SHARED_LIBRARIES_SETUP_CODE, //
        "return [modern_info]");
    assertThat(PyProviderUtils.getUsesSharedLibraries(getTarget())).isFalse();
  }