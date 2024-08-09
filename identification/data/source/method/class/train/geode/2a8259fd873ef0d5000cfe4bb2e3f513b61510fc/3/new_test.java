  @Test
  public void getCallerClass() {
    assertThat(ResourceUtils.getCallerClass(1)).isEqualTo(getClass());
  }