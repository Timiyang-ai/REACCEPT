public static ProtocolNegotiator httpProxy(final SocketAddress proxyAddress,
      final @Nullable String proxyUsername, final @Nullable String proxyPassword,
      final ProtocolNegotiator negotiator) {
    Preconditions.checkNotNull(proxyAddress, "proxyAddress");
    Preconditions.checkNotNull(negotiator, "negotiator");
    class ProxyNegotiator implements ProtocolNegotiator {
      @Override
      public Handler newHandler(GrpcHttp2ConnectionHandler http2Handler) {
        HttpProxyHandler proxyHandler;
        if (proxyUsername == null || proxyPassword == null) {
          proxyHandler = new HttpProxyHandler(proxyAddress);
        } else {
          proxyHandler = new HttpProxyHandler(proxyAddress, proxyUsername, proxyPassword);
        }
        return new BufferUntilProxyTunnelledHandler(
            proxyHandler, negotiator.newHandler(http2Handler));
      }
    }

    return new ProxyNegotiator();
  }