private SkylarkDebuggingProtos.DebugEvent setBreakpoints(
      long sequenceNumber, SkylarkDebuggingProtos.SetBreakpointsRequest request) {
    threadHandler.setBreakpoints(
        request
            .getBreakpointList()
            .stream()
            .filter(b -> b.getConditionCase() == ConditionCase.LOCATION)
            .map(SkylarkDebuggingProtos.Breakpoint::getLocation)
            .collect(toImmutableSet()));
    return DebugEventHelper.setBreakpointsResponse(sequenceNumber);
  }