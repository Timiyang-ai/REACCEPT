  @Test
  public void replaceFailure_notFound() throws Exception {
    assertThat(strategy.replaceFailure(1, 1L, accessException)).isNull();

    verify(store).obliterate(1);
    verify(loaderWriter).load(1);
  }