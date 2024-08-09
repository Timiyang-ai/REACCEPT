public static BiFunction<WebsocketInbound, WebsocketOutbound, Publisher<Void>> newHandler(
      ConnectionAcceptor acceptor, int mtu) {
    return (in, out) -> {
      DuplexConnection connection = new WebsocketDuplexConnection((Connection) in);
      if (mtu > 0) {
        connection =
            new FragmentationDuplexConnection(
                connection, ByteBufAllocator.DEFAULT, mtu, false, "server");
      }
      return acceptor.apply(connection).then(out.neverComplete());
    };
  }