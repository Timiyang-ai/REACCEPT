public static ProtocolNegotiator tls(final SslContext sslContext,
                                       final InetSocketAddress inetAddress) {
    Preconditions.checkNotNull(sslContext, "sslContext");
    Preconditions.checkNotNull(inetAddress, "inetAddress");

    return new ProtocolNegotiator() {
      @Override
      public Handler newHandler(Http2ConnectionHandler handler) {
        ChannelHandler sslBootstrap = new ChannelHandlerAdapter() {
          @Override
          public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            final SSLEngine sslEngine;
            if (sslContext instanceof OpenSslContext) {
              // TODO(nmittler): Unsupported for OpenSSL in Netty < 4.1.Beta6.
              // Until we upgrade Netty, uncomment the line below when testing with OpenSSL.
              //sslEngine = sslContext.newEngine(ctx.alloc());
              sslEngine = sslContext.newEngine(ctx.alloc(),
                  inetAddress.getHostName(), inetAddress.getPort());
            } else {
              sslEngine = sslContext.newEngine(ctx.alloc(),
                  inetAddress.getHostName(), inetAddress.getPort());
            }
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