boolean tryAssociate(TraceContext newContext) {
      synchronized (this) {
        if (context == null) {
          context = newContext;
          return true;
        }
        return context.traceId() == newContext.traceId()
            && context.spanId() == newContext.spanId();
      }
    }