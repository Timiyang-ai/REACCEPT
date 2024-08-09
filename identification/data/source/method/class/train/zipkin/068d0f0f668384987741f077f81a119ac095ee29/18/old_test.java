  @Test public void spanName_coercesAllToNull() {
    assertThat(queryBuilder.spanName("all").build().spanName())
      .isNull();
  }