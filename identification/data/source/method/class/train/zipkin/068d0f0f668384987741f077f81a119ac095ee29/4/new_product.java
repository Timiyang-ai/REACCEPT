@Nullable public Boolean debug() {
    return (flags & FLAG_DEBUG_SET) == FLAG_DEBUG_SET
      ? (flags & FLAG_DEBUG) == FLAG_DEBUG
      : null;
  }