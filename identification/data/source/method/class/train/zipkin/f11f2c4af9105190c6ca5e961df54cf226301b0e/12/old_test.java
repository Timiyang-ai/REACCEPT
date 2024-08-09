  @Test
  public void annotationKeys_emptyRequest() {
    assertThat(CassandraUtil.annotationKeys(request)).isEmpty();
  }