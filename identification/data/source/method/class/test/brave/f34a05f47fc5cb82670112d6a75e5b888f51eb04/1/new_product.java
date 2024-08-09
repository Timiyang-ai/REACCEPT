static SamplingFlags toSamplingFlags(int flags) {
    switch (flags) {
      case 0:
        return EMPTY;
      case FLAG_SAMPLED_SET:
        return NOT_SAMPLED;
      case FLAG_SAMPLED_SET | FLAG_SAMPLED:
        return SAMPLED;
      case FLAG_SAMPLED_SET | FLAG_SAMPLED | FLAG_DEBUG:
        return DEBUG;
      case FLAG_SAMPLED_LOCAL:
        return EMPTY_SAMPLED_LOCAL;
      case FLAG_SAMPLED_LOCAL | FLAG_SAMPLED_SET:
        return NOT_SAMPLED_SAMPLED_LOCAL;
      case FLAG_SAMPLED_LOCAL | FLAG_SAMPLED_SET | FLAG_SAMPLED:
        return SAMPLED_SAMPLED_LOCAL;
      case FLAG_SAMPLED_LOCAL | FLAG_SAMPLED_SET | FLAG_SAMPLED | FLAG_DEBUG:
        return DEBUG_SAMPLED_LOCAL;
      default:
        assert false; // programming error, but build anyway
        return new SamplingFlags(flags);
    }
  }