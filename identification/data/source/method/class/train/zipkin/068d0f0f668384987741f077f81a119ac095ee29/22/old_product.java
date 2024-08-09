public Builder parentId(@Nullable String parentId) {
      if (parentId != null) {
        int length = parentId.length();
        if (length > 16) throw new IllegalArgumentException("parentId.length > 16");
        validateHex(parentId);
        this.parentId = length < 16 ? padLeft(parentId, 16) : parentId;
      }
      return this;
    }