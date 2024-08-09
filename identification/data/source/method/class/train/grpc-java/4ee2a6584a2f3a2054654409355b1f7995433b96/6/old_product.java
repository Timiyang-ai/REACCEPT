public static ProtocolNegotiator tls(SslContext sslContext) {
    return new TlsNegotiator(sslContext);
  }