static SamplingFlags toSamplingFlags(int flags) {
    return flags == 0 ? EMPTY : SamplingFlags.debug(flags) ? DEBUG
        : (flags & FLAG_SAMPLED) == FLAG_SAMPLED ? SAMPLED : NOT_SAMPLED;
  }