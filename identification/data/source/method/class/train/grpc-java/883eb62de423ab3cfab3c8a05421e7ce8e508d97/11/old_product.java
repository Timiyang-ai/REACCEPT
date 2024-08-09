public static SSLSocket upgrade(SSLSocketFactory sslSocketFactory,
      HostnameVerifier hostnameVerifier, Socket socket, String host, int port,
      ConnectionSpec spec) throws IOException {
    Preconditions.checkNotNull(sslSocketFactory, "sslSocketFactory");
    Preconditions.checkNotNull(socket, "socket");
    Preconditions.checkNotNull(spec, "spec");
    SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(
        socket, host, port, true /* auto close */);
    spec.apply(sslSocket, false);
    String negotiatedProtocol = OkHttpProtocolNegotiator.get().negotiate(
        sslSocket, host, spec.supportsTlsExtensions() ? TLS_PROTOCOLS : null);
    Preconditions.checkState(
        TLS_PROTOCOLS.contains(Protocol.get(negotiatedProtocol)),
        "Only " + TLS_PROTOCOLS + " are supported, but negotiated protocol is %s",
        negotiatedProtocol);

    if (hostnameVerifier == null) {
      hostnameVerifier = OkHostnameVerifier.INSTANCE;
    }
    if (!hostnameVerifier.verify(host, sslSocket.getSession())) {
      throw new SSLPeerUnverifiedException("Cannot verify hostname: " + host);
    }
    return sslSocket;
  }