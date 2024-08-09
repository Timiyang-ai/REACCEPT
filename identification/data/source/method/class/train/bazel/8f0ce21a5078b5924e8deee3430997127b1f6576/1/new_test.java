  @Test
  public void getImports_ModernProvider() throws Exception {
    declareTargetWithImplementation( //
        IMPORTS_SETUP_CODE, //
        "return [modern_info]");
    assertThat(PyProviderUtils.getImports(getTarget())).containsExactly("abc");
  }