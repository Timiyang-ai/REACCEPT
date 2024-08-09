@Override
  public ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers, CallOptions
      callOptions) {
    Supplier<ClientTransport> supplier = transportSupplier;
    if (supplier == null) {
      synchronized (lock) {
        // Check again, since it may have changed while waiting for lock
        supplier = transportSupplier;
        if (supplier == null && !shutdown) {
          PendingStream pendingStream = new PendingStream(method, headers, callOptions);
          pendingStreams.add(pendingStream);
          return pendingStream;
        }
      }
    }
    if (supplier != null) {
      return supplier.get().newStream(method, headers, callOptions);
    }
    return new FailingClientStream(Status.UNAVAILABLE.withDescription("transport shutdown"));
  }