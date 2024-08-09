public static BiFunction<WebsocketInbound, WebsocketOutbound, Publisher<Void>> newHandler(
      ConnectionAcceptor acceptor) {
    return (in, out) -> {
      WebsocketDuplexConnection connection = new WebsocketDuplexConnection(in, out, in.context());
      acceptor.apply(connection).subscribe();

      return out.neverComplete();
    };
  }