  @Test public void deleteSecretSeries_notFound() throws Exception {
    assertThat(deleteSeries("non-existent").code()).isEqualTo(404);
  }