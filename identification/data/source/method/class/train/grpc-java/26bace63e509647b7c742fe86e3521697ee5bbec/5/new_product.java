@Override
  public final ClientStream newStream(MethodDescriptor<?, ?> method, Metadata headers,
      CallOptions callOptions, StatsTraceContext statsTraceCtx) {
    try {
      SubchannelPicker picker = null;
      PickSubchannelArgs args = new PickSubchannelArgsImpl(method, headers, callOptions);
      long pickerVersion = -1;
      synchronized (lock) {
        if (!shutdown) {
          if (lastPicker == null) {
            return createPendingStream(args, statsTraceCtx);
          }
          picker = lastPicker;
          pickerVersion = lastPickerVersion;
        }
      }
      if (picker != null) {
        while (true) {
          PickResult pickResult = picker.pickSubchannel(args);
          ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickResult,
              callOptions.isWaitForReady());
          if (transport != null) {
            return transport.newStream(args.getMethodDescriptor(), args.getHeaders(),
                args.getCallOptions(), statsTraceCtx);
          }
          // This picker's conclusion is "buffer".  If there hasn't been a newer picker set
          // (possible race with reprocess()), we will buffer it.  Otherwise, will try with the new
          // picker.
          synchronized (lock) {
            if (shutdown) {
              break;
            }
            if (pickerVersion == lastPickerVersion) {
              return createPendingStream(args, statsTraceCtx);
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