  @Test public void upgrade_grpcExp() {
    assertTrue(
        OkHttpTlsUpgrader.TLS_PROTOCOLS.indexOf(Protocol.GRPC_EXP) == -1
            || OkHttpTlsUpgrader.TLS_PROTOCOLS.indexOf(Protocol.GRPC_EXP)
                < OkHttpTlsUpgrader.TLS_PROTOCOLS.indexOf(Protocol.HTTP_2));
  }