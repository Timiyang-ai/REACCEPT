public static String negotiate(
      SSLSocket sslSocket, String hostname, List<Protocol> protocols) throws IOException {
    configureTlsExtensions(sslSocket, hostname, protocols);

    // It's possible that the user provided SSLSocketFactory has already done the handshake
    // when creates the SSLSocket.
    String negotiatedProtocol = getSelectedProtocol(sslSocket);
    if (negotiatedProtocol == null) {
      try {
        // Force handshake.
        sslSocket.startHandshake();

        negotiatedProtocol = OkHttpProtocolNegotiator.getSelectedProtocol(sslSocket);
        if (negotiatedProtocol == null) {
          throw new RuntimeException("protocol negotiation failed");
        }
      } finally {
        afterHandshake(sslSocket);
      }
    }
    return negotiatedProtocol;
  }