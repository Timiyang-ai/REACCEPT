public Span toSpan(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    if (alwaysSampleLocal) {
      int flags = InternalPropagation.instance.flags(context);
      if ((flags & FLAG_SAMPLED_LOCAL) != FLAG_SAMPLED_LOCAL) {
        context = InternalPropagation.instance.withFlags(context, flags | FLAG_SAMPLED_LOCAL);
      }
    }
    // decorating here addresses join, new traces or children and ad-hoc trace contexts
    return _toSpan(propagationFactory.decorate(context));
  }