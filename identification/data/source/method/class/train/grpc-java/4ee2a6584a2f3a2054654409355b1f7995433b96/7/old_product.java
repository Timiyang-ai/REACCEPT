public static ProtocolNegotiator tls(SslContext sslContext, String authority) {
    Preconditions.checkNotNull(sslContext, "sslContext");
    URI uri = GrpcUtil.authorityToUri(Preconditions.checkNotNull(authority, "authority"));
    String host;
    int port;
    if (uri.getHost() != null) {
      host = uri.getHost();
      port = uri.getPort();
    } else {
      /*
       * Implementation note: We pick -1 as the port here rather than deriving it from the original
       * socket address.  The SSL engine doens't use this port number when contacting the remote
       * server, but rather it is used for other things like SSL Session caching.  When an invalid
       * authority is provided (like "bad_cert"), picking the original port and passing it in would
       * mean that the port might used under the assumption that it was correct.   By using -1 here,
       * it forces the SSL implementation to treat it as invalid.
       */
      host = authority;
      port = -1;
    }

    return new TlsNegotiator(sslContext, host, port);
  }