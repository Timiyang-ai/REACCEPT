public static <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
        Context context,
        MethodDescriptor<ReqT, RespT> method,
        ServerCall<RespT> call,
        Metadata headers,
        ServerCallHandler<ReqT, RespT> next) {
    Context previous = context.attach();
    try {
      return new ContextualizedServerCallListener<ReqT>(
          next.startCall(method, call, headers),
          context);
    } finally {
      context.detach(previous);
    }
  }