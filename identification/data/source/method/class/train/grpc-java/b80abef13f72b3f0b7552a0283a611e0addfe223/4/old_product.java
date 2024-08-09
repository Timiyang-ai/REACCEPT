public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(
      Channel channel, MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, ReqT param) {
    ThreadlessExecutor executor = new ThreadlessExecutor();
    ClientCall<ReqT, RespT> call = channel.newCall(method, callOptions.withExecutor(executor));
    BlockingResponseStream<RespT> result = new BlockingResponseStream<RespT>(call, executor);
    asyncUnaryRequestCall(call, param, result.listener(), true);
    return result;
  }