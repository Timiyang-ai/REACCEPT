public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(
      ClientCall<ReqT, RespT> call, ReqT param) {
    BlockingResponseStream<RespT> result = new BlockingResponseStream<RespT>(call);
    asyncServerStreamingCall(call, param, result.listener());
    return result;
  }