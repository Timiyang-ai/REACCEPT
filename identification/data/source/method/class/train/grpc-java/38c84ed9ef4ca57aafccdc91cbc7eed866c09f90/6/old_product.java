@VisibleForTesting
  final List<ClientInterceptor> getEffectiveInterceptors() {
    List<ClientInterceptor> effectiveInterceptors =
        new ArrayList<ClientInterceptor>(this.interceptors);
    temporarilyDisableRetry = false;
    if (statsEnabled) {
      temporarilyDisableRetry = true;
      CensusStatsModule censusStats = this.censusStatsOverride;
      if (censusStats == null) {
        censusStats = new CensusStatsModule(GrpcUtil.STOPWATCH_SUPPLIER, true);
      }
      // First interceptor runs last (see ClientInterceptors.intercept()), so that no
      // other interceptor can override the tracer factory we set in CallOptions.
      effectiveInterceptors.add(
          0, censusStats.getClientInterceptor(recordStartedRpcs, recordFinishedRpcs));
    }
    if (tracingEnabled) {
      temporarilyDisableRetry = true;
      CensusTracingModule censusTracing =
          new CensusTracingModule(Tracing.getTracer(),
              Tracing.getPropagationComponent().getBinaryFormat());
      effectiveInterceptors.add(0, censusTracing.getClientInterceptor());
    }
    return effectiveInterceptors;
  }