public Builder debug(@Nullable Boolean debug) {
      if (debug != null) return debug((boolean) debug);
      flags &= ~FLAG_DEBUG_SET;
      return this;
    }