public static SSLSocket upgrade(SSLSocketFactory sslSocketFactory,
      Socket socket, String host, int port, ConnectionSpec spec) throws IOException {
    Preconditions.checkNotNull(sslSocketFactory);
    Preconditions.checkNotNull(socket);
    Preconditions.checkNotNull(spec);
    SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(
        socket, host, port, true /* auto close */);
    spec.apply(sslSocket, getOkHttpRoute(host, port, spec));

    Platform platform = Platform.get();

    // It's possible that the user provided SSLSocketFactory has already done the handshake
    // when creates the SSLSocket.
    String negotiatedProtocol = SelectedProtocolQuerier.getSelectedProtocol(sslSocket);
    if (negotiatedProtocol == null) {
      try {
        // Force handshake.
        sslSocket.startHandshake();

        negotiatedProtocol = SelectedProtocolQuerier.getSelectedProtocol(sslSocket);
        if (negotiatedProtocol == null) {
          throw new RuntimeException("protocol negotiation failed");
        }
      } finally {
        platform.afterHandshake(sslSocket);
      }
    }

    Preconditions.checkState(SUPPORTED_HTTP2_PROTOCOLS.contains(negotiatedProtocol),
        "negotiated protocol %s is unsupported", negotiatedProtocol);

    return sslSocket;
  }