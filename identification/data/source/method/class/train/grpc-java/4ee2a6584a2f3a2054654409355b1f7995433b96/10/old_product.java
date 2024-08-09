public static ProtocolNegotiator tls(final SslContext sslContext,
                                       String authority) {
    Preconditions.checkNotNull(sslContext, "sslContext");
    final URI uri = GrpcUtil.authorityToUri(Preconditions.checkNotNull(authority, "authority"));

    return new ProtocolNegotiator() {
      @Override
      public Handler newHandler(Http2ConnectionHandler handler) {
        ChannelHandler sslBootstrap = new ChannelHandlerAdapter() {
          @Override
          public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            SSLEngine sslEngine = sslContext.newEngine(ctx.alloc(), uri.getHost(), uri.getPort());
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