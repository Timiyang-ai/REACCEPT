  @Test public void remoteEndpoint_emptyToNull() {
    assertThat(base.toBuilder().remoteEndpoint(Endpoint.newBuilder().build()).remoteEndpoint)
      .isNull();
  }