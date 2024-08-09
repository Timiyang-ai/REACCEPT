public static SSLSocket upgrade(SSLSocketFactory sslSocketFactory,
      Socket socket, String host, int port, ConnectionSpec spec) throws IOException {
    Preconditions.checkNotNull(sslSocketFactory);
    Preconditions.checkNotNull(socket);
    Preconditions.checkNotNull(spec);
    SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(
        socket, host, port, true /* auto close */);
    spec.apply(sslSocket, false);
    if (spec.supportsTlsExtensions()) {
      String negotiatedProtocol =
          OkHttpProtocolNegotiator.get().negotiate(sslSocket, host, TLS_PROTOCOLS);
      Preconditions.checkState(HTTP2_PROTOCOL_NAME.equals(negotiatedProtocol),
          "Only \"h2\" is supported, but negotiated protocol is %s", negotiatedProtocol);
    }
    return sslSocket;
  }