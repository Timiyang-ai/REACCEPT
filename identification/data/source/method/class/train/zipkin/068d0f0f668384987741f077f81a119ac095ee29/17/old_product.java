public Builder parentId(@Nullable String parentId) {
      this.parentId = parentId != null ? lowerHexToUnsignedLong(parentId) : null;
      return this;
    }