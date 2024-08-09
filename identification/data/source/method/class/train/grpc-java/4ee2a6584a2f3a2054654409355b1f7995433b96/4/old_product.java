public static ProtocolNegotiator tls(final SslContext sslContext,
                                       final InetSocketAddress inetAddress) {
    Preconditions.checkNotNull(sslContext, "sslContext");
    Preconditions.checkNotNull(inetAddress, "inetAddress");

    final ChannelHandler sslBootstrapHandler = new ChannelHandlerAdapter() {
      @Override
      public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // TODO(nmittler): This method is currently unsupported for OpenSSL. Need to fix in Netty.
        SSLEngine sslEngine = sslContext.newEngine(ctx.alloc(),
            inetAddress.getHostName(), inetAddress.getPort());
        SSLParameters sslParams = new SSLParameters();
        sslParams.setEndpointIdentificationAlgorithm("HTTPS");
        sslEngine.setSSLParameters(sslParams);

        final SettableFuture<Void> completeFuture = SettableFuture.create();
        if (isOpenSsl(sslContext.getClass())) {
          completeFuture.set(null);
        } else {
          // Using JDK SSL
          if (!installJettyTlsProtocolSelection(sslEngine, completeFuture, false)) {
            throw new IllegalStateException("NPN/ALPN extensions not installed");
          }
        }

        SslHandler sslHandler = new SslHandler(sslEngine, false);
        sslHandler.handshakeFuture().addListener(
            new GenericFutureListener<Future<? super Channel>>() {
              @Override
              public void operationComplete(Future<? super Channel> future) throws Exception {
                // If an error occurred during the handshake, throw it to the pipeline.
                if (future.isSuccess()) {
                  completeFuture.get();
                } else {
                  future.get();
                }
              }
            });
        ctx.pipeline().replace(this, "sslHandler", sslHandler);
      }
    };
    return new ProtocolNegotiator() {
      @Override
      public Handler newHandler(Http2ConnectionHandler handler) {
        return new BufferUntilTlsNegotiatedHandler(sslBootstrapHandler, handler);
      }
    };
  }