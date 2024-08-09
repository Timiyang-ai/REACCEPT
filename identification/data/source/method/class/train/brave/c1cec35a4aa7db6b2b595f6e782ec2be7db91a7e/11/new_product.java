public Span toSpan(TraceContext context) {
    return _toSpan(decorateExternal(context));
  }