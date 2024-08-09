@Override
  public final ClientStream newStream(
      MethodDescriptor<?, ?> method, Metadata headers, CallOptions callOptions) {
    try {
      SubchannelPicker picker;
      PickSubchannelArgs args = new PickSubchannelArgsImpl(method, headers, callOptions);
      long pickerVersion = -1;
      synchronized (lock) {
        if (shutdownStatus == null) {
          if (lastPicker == null) {
            return createPendingStream(args);
          }
          picker = lastPicker;
          pickerVersion = lastPickerVersion;
        } else {
          return new FailingClientStream(shutdownStatus);
        }
      }
      while (true) {
        PickResult pickResult = picker.pickSubchannel(args);
        ClientTransport transport = GrpcUtil.getTransportFromPickResult(pickResult,
            callOptions.isWaitForReady());
        if (transport != null) {
          return transport.newStream(
              args.getMethodDescriptor(), args.getHeaders(), args.getCallOptions());
        }
        // This picker's conclusion is "buffer".  If there hasn't been a newer picker set (possible
        // race with reprocess()), we will buffer it.  Otherwise, will try with the new picker.
        synchronized (lock) {
          if (shutdownStatus != null) {
            return new FailingClientStream(shutdownStatus);
          }
          if (pickerVersion == lastPickerVersion) {
            return createPendingStream(args);
          }
          picker = lastPicker;
          pickerVersion = lastPickerVersion;
        }
      }
    } finally {
      channelExecutor.drain();
    }
  }