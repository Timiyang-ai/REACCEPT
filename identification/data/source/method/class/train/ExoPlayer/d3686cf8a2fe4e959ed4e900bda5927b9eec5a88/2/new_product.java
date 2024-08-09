public DataSpec withUri(Uri uri) {
    return new DataSpec(
        uri, httpMethod, httpBody, absoluteStreamPosition, position, length, key, flags);
  }