public Builder debug(@Nullable Boolean debug) {
      if (debug != null) return debug(debug);
      flags &= ~FLAG_DEBUG_SET;
      return this;
    }