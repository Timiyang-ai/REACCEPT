  @Test public void ipv6_notMappedIpv4() {
    String ipv6 = "::ffef:43.0.192.2";
    Endpoint endpoint = Endpoint.newBuilder().ip(ipv6).build();

    assertThat(endpoint.ipv4())
      .isNull();
    assertThat(endpoint.ipv4Bytes())
      .isNull();
    assertThat(endpoint.ipv6())
      .isNull();
    assertThat(endpoint.ipv6Bytes())
      .isNull();
  }