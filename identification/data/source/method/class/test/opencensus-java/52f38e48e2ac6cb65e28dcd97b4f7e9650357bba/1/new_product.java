public static SpanContext fromCloudTraceContext(CloudTraceContext cloudTraceContext) {
    checkNotNull(cloudTraceContext, "cloudTraceContext");

    try {
      // Extract the trace ID from the binary protobuf CloudTraceContext#traceId.
      TraceIdProto traceIdProto = TraceIdProto.parseFrom(cloudTraceContext.getTraceId());
      ByteBuffer traceIdBuf = ByteBuffer.allocate(TraceId.SIZE);
      traceIdBuf.putLong(traceIdProto.getHi());
      traceIdBuf.putLong(traceIdProto.getLo());
      ByteBuffer spanIdBuf = ByteBuffer.allocate(SpanId.SIZE);
      spanIdBuf.putLong(cloudTraceContext.getSpanId());

      return SpanContext.create(
          TraceId.fromBytes(traceIdBuf.array()),
          SpanId.fromBytes(spanIdBuf.array()),
          TraceOptions.builder().setIsSampled(cloudTraceContext.isTraceEnabled()).build(),
          TRACESTATE_DEFAULT);
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e);
    }
  }