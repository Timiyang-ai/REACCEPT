public static ProtocolNegotiator tls(final SslContext sslContext,
                                       final InetSocketAddress inetAddress) {
    Preconditions.checkNotNull(sslContext, "sslContext");
    Preconditions.checkNotNull(inetAddress, "inetAddress");

    // If we're using Jetty ALPN, verify that it is configured properly.
    if (!(sslContext instanceof OpenSslContext)) {
      try {
        JettyAlpnVerifier.verifyJettyAlpn();
      } catch (JettyAlpnVerifier.NotFoundException e) {
        throw new IllegalArgumentException(e);
      }
    }

    return new ProtocolNegotiator() {
      @Override
      public Handler newHandler(Http2ConnectionHandler handler) {
        ChannelHandler sslBootstrap = new ChannelHandlerAdapter() {
          @Override
          public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            // TODO(nmittler): Unsupported for OpenSSL in Netty < 4.1.Beta6.
            SSLEngine sslEngine = sslContext.newEngine(ctx.alloc(),
                    inetAddress.getHostName(), inetAddress.getPort());
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            sslEngine.setSSLParameters(sslParams);
            ctx.pipeline().replace(this, null, new SslHandler(sslEngine, false));
          }
        };
        return new BufferUntilTlsNegotiatedHandler(sslBootstrap, handler);
      }
    };
  }