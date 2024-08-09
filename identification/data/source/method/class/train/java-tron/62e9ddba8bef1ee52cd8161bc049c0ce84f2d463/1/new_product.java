public void setParentHash(final String parentHash) {
    this.parentHash = parentHash;

    if (this.parentHash == null) {
      this.parentHash = DEFAULT_PARENT_HASH;
    }
  }