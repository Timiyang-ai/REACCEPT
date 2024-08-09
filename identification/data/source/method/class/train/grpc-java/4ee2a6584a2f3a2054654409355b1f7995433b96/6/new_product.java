public static ProtocolNegotiator tls(SslContext sslContext) {
    return new ClientTlsProtocolNegotiator(sslContext);
  }