@Nullable public final Boolean sampled() {
    return (flags & FLAG_SAMPLED_SET) == FLAG_SAMPLED_SET
        ? (flags & FLAG_SAMPLED) == FLAG_SAMPLED
        : null;
  }