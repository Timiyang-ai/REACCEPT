  @Test
  public void spread() throws Throwable {
    assertThat(collect.handle().invoke(1, 2, 3), is("123"));
    assertThat(collect.spread(1, 2, 3), is("123"));
  }