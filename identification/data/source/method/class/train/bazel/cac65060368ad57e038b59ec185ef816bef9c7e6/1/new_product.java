private SkylarkDebuggingProtos.DebugEvent setBreakpoints(
      long sequenceNumber, SkylarkDebuggingProtos.SetBreakpointsRequest request) {
    threadHandler.setBreakpoints(request.getBreakpointList());
    return DebugEventHelper.setBreakpointsResponse(sequenceNumber);
  }