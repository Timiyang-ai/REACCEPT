public static ProtocolNegotiator httpProxy(final SocketAddress proxyAddress,
      final @Nullable String proxyUsername, final @Nullable String proxyPassword,
      final ProtocolNegotiator negotiator) {
    final AsciiString scheme = negotiator.scheme();
    Preconditions.checkNotNull(proxyAddress, "proxyAddress");
    Preconditions.checkNotNull(negotiator, "negotiator");
    class ProxyNegotiator implements ProtocolNegotiator {
      @Override
      public ChannelHandler newHandler(GrpcHttp2ConnectionHandler http2Handler) {
        HttpProxyHandler proxyHandler;
        if (proxyUsername == null || proxyPassword == null) {
          proxyHandler = new HttpProxyHandler(proxyAddress);
        } else {
          proxyHandler = new HttpProxyHandler(proxyAddress, proxyUsername, proxyPassword);
        }
        return new BufferUntilProxyTunnelledHandler(
            proxyHandler, negotiator.newHandler(http2Handler));
      }

      @Override
      public AsciiString scheme() {
        return scheme;
      }

      // This method is not normally called, because we use httpProxy on a per-connection basis in
      // NettyChannelBuilder. Instead, we expect `negotiator' to be closed by NettyTransportFactory.
      @Override
      public void close() {
        negotiator.close();
      }
    }

    return new ProxyNegotiator();
  }