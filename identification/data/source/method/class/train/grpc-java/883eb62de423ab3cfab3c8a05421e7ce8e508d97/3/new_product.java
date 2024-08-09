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
      Preconditions.checkState(SUPPORTED_HTTP2_PROTOCOLS.contains(negotiatedProtocol),
          "negotiated protocol %s is unsupported", negotiatedProtocol);
    }
    return sslSocket;
  }