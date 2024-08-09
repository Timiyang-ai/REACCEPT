  @Test
  public void blockingServerStreamingCall_interruptedWaitsForOnClose() throws Exception {
    Integer req = 2;

    class NoopServerStreamingMethod implements ServerStreamingMethod<Integer, Integer> {
      ServerCallStreamObserver<Integer> observer;

      @Override public void invoke(Integer request, StreamObserver<Integer> responseObserver) {
        observer = (ServerCallStreamObserver<Integer>) responseObserver;
      }
    }

    NoopServerStreamingMethod methodImpl = new NoopServerStreamingMethod();
    server = InProcessServerBuilder.forName("noop").directExecutor()
        .addService(ServerServiceDefinition.builder("some")
            .addMethod(SERVER_STREAMING_METHOD, ServerCalls.asyncServerStreamingCall(methodImpl))
            .build())
        .build().start();

    InterruptInterceptor interceptor = new InterruptInterceptor();
    channel = InProcessChannelBuilder.forName("noop")
        .directExecutor()
        .intercept(interceptor)
        .build();
    Iterator<Integer> iter = ClientCalls.blockingServerStreamingCall(
        channel.newCall(SERVER_STREAMING_METHOD, CallOptions.DEFAULT), req);
    try {
      iter.next();
      fail();
    } catch (StatusRuntimeException ex) {
      assertTrue(Thread.interrupted());
      assertTrue("interrupted", ex.getCause() instanceof InterruptedException);
    }
    assertTrue("onCloseCalled", interceptor.onCloseCalled);
    assertTrue("context not cancelled", methodImpl.observer.isCancelled());
  }