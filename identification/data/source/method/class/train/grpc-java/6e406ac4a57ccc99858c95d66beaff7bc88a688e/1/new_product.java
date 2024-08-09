public String negotiate(
      SSLSocket sslSocket, String hostname, @Nullable List<Protocol> protocols) throws IOException {
    if (protocols != null) {
      configureTlsExtensions(sslSocket, hostname, protocols);
    }
    try {
      // Force handshake.
      sslSocket.startHandshake();

      String negotiatedProtocol = getSelectedProtocol(sslSocket);
      if (negotiatedProtocol == null) {
        throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + protocols);
      }
      return negotiatedProtocol;
    } finally {
      platform.afterHandshake(sslSocket);
    }
  }