@Nullable
  static Span getCurrentSpan() {
    return ContextUtils.getValue(Context.current());
  }