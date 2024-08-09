  @Test public void selectApplicationProtocolConfig_grpcExp() {
    assertTrue(
        GrpcSslContexts.NEXT_PROTOCOL_VERSIONS.indexOf("grpc-exp") == -1
            || GrpcSslContexts.NEXT_PROTOCOL_VERSIONS.indexOf("grpc-exp")
                < GrpcSslContexts.NEXT_PROTOCOL_VERSIONS.indexOf("h2"));
  }