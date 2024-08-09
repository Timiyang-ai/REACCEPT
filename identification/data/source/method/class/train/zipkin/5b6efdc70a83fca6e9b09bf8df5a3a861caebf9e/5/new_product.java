@Nullable public Boolean shared() {
    return (flags & FLAG_SHARED_SET) == FLAG_SHARED_SET
      ? (flags & FLAG_SHARED) == FLAG_SHARED
      : null;
  }