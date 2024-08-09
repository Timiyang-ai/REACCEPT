@Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof RealSpan)) return false;
    return context.equals(((RealSpan) o).context);
  }