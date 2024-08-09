  @Test
  public void putIfAbsentFailure_found() throws Exception {
    when(loaderWriter.load(1)).thenReturn(1L);

    assertThat(strategy.putIfAbsentFailure(1, 2L, accessException)).isEqualTo(1);

    verify(store).obliterate(1);
    verify(loaderWriter).load(1);
  }