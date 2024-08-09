public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(
      ClientCall<ReqT, RespT> call, ReqT req) {
    BlockingResponseStream<RespT> result = new BlockingResponseStream<>(call);
    asyncUnaryRequestCall(call, req, result.listener(), true);
    return result;
  }