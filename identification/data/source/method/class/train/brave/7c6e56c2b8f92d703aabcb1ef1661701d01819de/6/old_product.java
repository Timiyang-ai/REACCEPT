public Span newTrace() {
    return _toSpan(newRootContext());
  }