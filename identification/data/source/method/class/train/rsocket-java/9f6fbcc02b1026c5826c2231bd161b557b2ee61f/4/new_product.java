static BiFunction<WebsocketInbound, WebsocketOutbound, Publisher<Void>> newHandler(
      ConnectionAcceptor acceptor) {

    Objects.requireNonNull(acceptor, "acceptor must not be null");

    return (in, out) -> {
      WebsocketDuplexConnection connection = new WebsocketDuplexConnection(in, out, in.context());
      return acceptor.apply(connection).then(out.neverComplete());
    };
  }