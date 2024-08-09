@Override public boolean equals(Object o) {
    if (o == this) return true;
    if (o instanceof LazySpan) {
      return context.equals(((LazySpan) o).context);
    } else if (o instanceof RealSpan) {
      return context.equals(((RealSpan) o).context);
    }
    return false;
  }