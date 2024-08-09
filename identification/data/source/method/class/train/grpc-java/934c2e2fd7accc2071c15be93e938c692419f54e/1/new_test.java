  @Test
  public void wrapChannel_methodDescriptor() throws Exception {
    final AtomicReference<MethodDescriptor<?, ?>> methodRef =
        new AtomicReference<>();
    Channel channel = new Channel() {
      @Override
      public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(
          MethodDescriptor<RequestT, ResponseT> method, CallOptions callOptions) {
        methodRef.set(method);
        return new NoopClientCall<>();
      }

      @Override
      public String authority() {
        throw new UnsupportedOperationException();
      }
    };
    Channel wChannel = binlogProvider.wrapChannel(channel);
    ClientCall<String, Integer> unusedClientCall = wChannel.newCall(method, CallOptions.DEFAULT);
    validateWrappedMethod(methodRef.get());
  }