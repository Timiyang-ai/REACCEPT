public static <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
        Context context,
        ServerCall<ReqT, RespT> call,
        Metadata headers,
        ServerCallHandler<ReqT, RespT> next) {
    Context previous = context.attach();
    try {
      return new ContextualizedServerCallListener<ReqT>(
          next.startCall(call, headers),
          context);
    } finally {
      context.detach(previous);
    }
  }