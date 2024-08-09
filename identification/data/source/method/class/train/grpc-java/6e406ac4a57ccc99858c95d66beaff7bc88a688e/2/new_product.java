@Override
    public String negotiate(SSLSocket sslSocket, String hostname, List<Protocol> protocols)
        throws IOException {
      // First check if a protocol has already been selected, since it's possible that the user
      // provided SSLSocketFactory has already done the handshake when creates the SSLSocket.
      String negotiatedProtocol = getSelectedProtocol(sslSocket);
      if (negotiatedProtocol == null) {
        negotiatedProtocol = super.negotiate(sslSocket, hostname, protocols);
      }
      return negotiatedProtocol;
    }