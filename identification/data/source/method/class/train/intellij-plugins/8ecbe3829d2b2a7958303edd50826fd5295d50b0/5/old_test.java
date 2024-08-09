  @Test
  public void trimActionPath() {
    assertThat(TaglibUtil.trimActionPath("noBang"), Is.is("noBang"));
    assertThat(TaglibUtil.trimActionPath("noBang!bang"), Is.is("noBang"));
    assertThat(TaglibUtil.trimActionPath("noBang!!bang"), Is.is("noBang"));
  }