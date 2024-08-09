@Override
  public final ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers,
      CallOptions callOptions, StatsTraceContext statsTraceCtx) {
    try {
      SubchannelPicker picker = null;
      long pickerVersion = -1;
      synchronized (lock) {
        if (!shutdown) {
          if (lastPicker == null) {
            return createPendingStream(method, headers, callOptions, statsTraceCtx);
          }
          picker = lastPicker;
          pickerVersion = lastPickerVersion;
        }
      }
      if (picker != null) {
        while (true) {
          PickResult pickResult = picker.pickSubchannel(callOptions.getAffinity(), headers);
          ClientTransport transport = GrpcUtil.getTransportFromPickResult(
              pickResult, callOptions.isWaitForReady());
          if (transport != null) {
            return transport.newStream(method, headers, callOptions, statsTraceCtx);
          }
          // This picker's conclusion is "buffer".  If there hasn't been a newer picker set
          // (possible race with reprocess()), we will buffer it.  Otherwise, will try with the new
          // picker.
          synchronized (lock) {
            if (shutdown) {
              break;
            }
            if (pickerVersion == lastPickerVersion) {
              return createPendingStream(method, headers, callOptions, statsTraceCtx);
            }
            picker = lastPicker;
            pickerVersion = lastPickerVersion;
          }
        }
      }
      return new FailingClientStream(Status.UNAVAILABLE.withDescription(
              "Channel has shutdown (reported by delayed transport)"));
    } finally {
      channelExecutor.drain();
    }
  }