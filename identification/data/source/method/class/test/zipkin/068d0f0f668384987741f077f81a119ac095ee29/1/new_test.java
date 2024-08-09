  @Test public void serviceName_coercesEmptyToNull() {
    assertThat(queryBuilder.serviceName("").build().serviceName())
      .isNull();
  }