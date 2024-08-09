public DataSpec subrange(long offset, long length) {
    if (offset == 0 && this.length == length) {
      return this;
    } else {
      return new DataSpec(uri, postBody, absoluteStreamPosition + offset, position + offset, length,
          key, flags);
    }
  }