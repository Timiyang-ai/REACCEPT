@Override
  public final ClientStream newStream(
      MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
    try {
      PickSubchannelArgs args = new PickSubchannelArgsImpl(method, headers, callOptions);
      SubchannelPicker picker = null;
      long pickerVersion = -1;
      while (true) {
        synchronized (lock) {
          if (shutdownStatus != null) {
            return new FailingClientStream(shutdownStatus);
          }
          if (lastPicker == null) {
            return createPendingStream(args);
          }
          // Check for second time through the loop, and whether anything changed
          if (picker != null && pickerVersion == lastPickerVersion) {
            return createPendingStream(args);
          }
          picker = lastPicker;
          pickerVersion = lastPickerVersion;
        }
        PickResult pickResult = picker.pickSubchannel(args);
        ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickResult,
            callOptions.isWaitForReady());
        if (transport != null) {
          return transport.newStream(
              args.getMethodDescriptor(), args.getHeaders(), args.getCallOptions());
        }
        // This picker's conclusion is "buffer".  If there hasn't been a newer picker set (possible
        // race with reprocess()), we will buffer it.  Otherwise, will try with the new picker.
      }
    } finally {
      syncContext.drain();
    }
  }