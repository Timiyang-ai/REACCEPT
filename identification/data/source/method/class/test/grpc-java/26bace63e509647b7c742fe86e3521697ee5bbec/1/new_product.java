@Override
  public final ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers,
      CallOptions callOptions, StatsTraceContext statsTraceCtx) {
    Supplier<ClientTransport> supplier = transportSupplier;
    if (supplier == null) {
      synchronized (lock) {
        // Check again, since it may have changed while waiting for lock
        supplier = transportSupplier;
        if (supplier == null && !shutdown) {
          if (backoffStatus != null && !callOptions.isWaitForReady()) {
            return new FailingClientStream(backoffStatus);
          }
          PendingStream pendingStream = new PendingStream(method, headers, callOptions,
              statsTraceCtx);
          pendingStreams.add(pendingStream);
          if (pendingStreams.size() == 1) {
            listener.transportInUse(true);
          }
          return pendingStream;
        }
      }
    }
    if (supplier != null) {
      return supplier.get().newStream(method, headers, callOptions, statsTraceCtx);
    }
    return new FailingClientStream(Status.UNAVAILABLE.withDescription("transport shutdown"));
  }