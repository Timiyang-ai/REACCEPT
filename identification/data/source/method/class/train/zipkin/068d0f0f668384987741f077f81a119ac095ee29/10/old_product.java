public Builder parentId(@Nullable String parentId) {
      if (parentId == null) {
        this.parentId = null;
        return this;
      }
      int length = parentId.length();
      if (length == 0) throw new IllegalArgumentException("parentId is empty");
      if (length > 16) throw new IllegalArgumentException("parentId.length > 16");
      validateHex(parentId);
      this.parentId = length < 16 ? padLeft(parentId, 16) : parentId;
      return this;
    }