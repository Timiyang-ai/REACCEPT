public DataSpec withUri(Uri uri) {
    return new DataSpec(uri, postBody, absoluteStreamPosition, position, length, key, flags);
  }