@Deprecated
  public TraceContextOrSamplingFlags sampled(@Nullable Boolean sampled) {
    if (sampled != null) return sampled(sampled.booleanValue());
    int flags = value.flags;
    flags &= ~FLAG_SAMPLED_SET;
    flags &= ~FLAG_SAMPLED;
    if (flags == value.flags) return this; // save effort if no change
    return withFlags(flags);
  }