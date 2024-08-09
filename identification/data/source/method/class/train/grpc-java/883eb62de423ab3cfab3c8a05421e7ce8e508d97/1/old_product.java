public static SSLSocket upgrade(SSLSocketFactory sslSocketFactory,
      Socket socket, String host, int port, ConnectionSpec spec) throws IOException {
    Preconditions.checkNotNull(sslSocketFactory);
    Preconditions.checkNotNull(socket);
    Preconditions.checkNotNull(spec);
    SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(
        socket, host, port, true /* auto close */);
    spec.apply(sslSocket, getOkHttpRoute(host, port, spec));

    Platform platform = Platform.get();
    try {
      // Force handshake.
      sslSocket.startHandshake();

      String negotiatedProtocol = platform.getSelectedProtocol(sslSocket);
      if (negotiatedProtocol == null) {
        throw new RuntimeException("protocol negotiation failed");
      }
      Preconditions.checkState(Protocol.HTTP_2.equals(Protocol.get(negotiatedProtocol)),
          "negotiated protocol is %s instead of %s.",
          negotiatedProtocol, Protocol.HTTP_2.toString());
    } finally {
      platform.afterHandshake(sslSocket);
    }

    return sslSocket;
  }