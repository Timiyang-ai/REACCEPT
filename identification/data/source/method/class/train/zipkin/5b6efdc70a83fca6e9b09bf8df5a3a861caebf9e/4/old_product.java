public Builder shared(@Nullable Boolean shared) {
      if (shared != null) return shared(shared);
      flags &= ~FLAG_SHARED_SET;
      return this;
    }