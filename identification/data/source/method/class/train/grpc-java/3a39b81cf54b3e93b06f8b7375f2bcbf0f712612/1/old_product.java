public static ProtocolNegotiator httpProxy(final SocketAddress proxyAddress,
      final @Nullable String proxyUsername, final @Nullable String proxyPassword,
      final ProtocolNegotiator negotiator) {
    checkNotNull(negotiator, "negotiator");
    checkNotNull(proxyAddress, "proxyAddress");
    final AsciiString scheme = negotiator.scheme();
    class ProxyNegotiator implements ProtocolNegotiator {
      @Override
      public ChannelHandler newHandler(GrpcHttp2ConnectionHandler http2Handler) {
        ChannelHandler protocolNegotiationHandler = negotiator.newHandler(http2Handler);
        ChannelHandler ppnh = new ProxyProtocolNegotiationHandler(
            proxyAddress, proxyUsername, proxyPassword, protocolNegotiationHandler);
        return ppnh;
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