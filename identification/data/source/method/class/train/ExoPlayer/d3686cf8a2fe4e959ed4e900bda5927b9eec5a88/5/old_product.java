public DataSpec subrange(long offset, long length) {
    if (offset == 0 && this.length == length) {
      return this;
    } else {
      return new DataSpec(
          uri,
          httpMethod,
          httpBody,
          absoluteStreamPosition + offset,
          position + offset,
          length,
          key,
          flags,
          httpRequestHeaders);
    }
  }