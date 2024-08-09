public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    // decorating here addresses join, new traces or children and ad-hoc trace contexts
    return _toSpan(propagationFactory.decorate(context));
  }