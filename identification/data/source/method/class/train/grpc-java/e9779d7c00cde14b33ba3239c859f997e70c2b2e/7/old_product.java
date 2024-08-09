final void reprocess(SubchannelPicker picker) {
    ArrayList<PendingStream> toProcess;
    ArrayList<PendingStream> toRemove = new ArrayList<PendingStream>();
    synchronized (lock) {
      lastPicker = picker;
      lastPickerVersion++;
      if (pendingStreams == null || pendingStreams.isEmpty()) {
        return;
      }
      toProcess = new ArrayList<PendingStream>(pendingStreams);
    }

    for (final PendingStream stream : toProcess) {
      PickResult pickResult = picker.pickSubchannel(
          stream.callOptions.getAffinity(), stream.headers);
      final ClientTransport transport = GrpcUtil.getTransportFromPickResult(
          pickResult, stream.callOptions.isWaitForReady());
      if (transport != null) {
        Executor executor = defaultAppExecutor;
        // createRealStream may be expensive. It will start real streams on the transport. If
        // there are pending requests, they will be serialized too, which may be expensive. Since
        // we are now on transport thread, we need to offload the work to an executor.
        if (stream.callOptions.getExecutor() != null) {
          executor = stream.callOptions.getExecutor();
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
              stream.createRealStream(transport);
            }
          });
        toRemove.add(stream);
      }  // else: stay pending
    }

    synchronized (lock) {
      // Between this synchronized and the previous one:
      //   - Streams may have been cancelled, which may turn pendingStreams into emptiness.
      //   - shutdown() may be called, which may turn pendingStreams into null.
      if (pendingStreams == null || pendingStreams.isEmpty()) {
        return;
      }
      pendingStreams.removeAll(toRemove);
      if (pendingStreams.isEmpty()) {
        // There may be a brief gap between delayed transport clearing in-use state, and first real
        // transport starting streams and setting in-use state.  During the gap the whole channel's
        // in-use state may be false. However, it shouldn't cause spurious switching to idleness
        // (which would shutdown the transports and LoadBalancer) because the gap should be shorter
        // than IDLE_MODE_DEFAULT_TIMEOUT_MILLIS (1 second).
        channelExecutor.executeLater(reportTransportNotInUse);
        if (shutdown) {
          pendingStreams = null;
          channelExecutor.executeLater(reportTransportTerminated);
        } else {
          // Because delayed transport is long-lived, we take this opportunity to down-size the
          // hashmap.
          pendingStreams = new LinkedHashSet<PendingStream>();
        }
      }
    }
    channelExecutor.drain();
  }