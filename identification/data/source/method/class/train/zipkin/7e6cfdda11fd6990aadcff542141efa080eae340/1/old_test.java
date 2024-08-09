  @Test public void localEndpoint_emptyToNull() {
    assertThat(base.toBuilder().localEndpoint(Endpoint.newBuilder().build()).localEndpoint)
      .isNull();
  }