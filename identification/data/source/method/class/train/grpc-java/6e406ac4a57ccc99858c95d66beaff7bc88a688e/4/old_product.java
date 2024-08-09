public String negotiate(
      SSLSocket sslSocket, String hostname, List<Protocol> protocols) throws IOException {
    configureTlsExtensions(sslSocket, hostname, protocols);
    try {
      // Force handshake.
      sslSocket.startHandshake();

      String negotiatedProtocol = getSelectedProtocol(sslSocket);
      if (negotiatedProtocol == null) {
        throw new RuntimeException("protocol negotiation failed");
      }
      return negotiatedProtocol;
    } finally {
      PLATFORM.afterHandshake(sslSocket);
    }
  }