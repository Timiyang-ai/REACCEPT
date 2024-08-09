public void remove(InodeFile file) {
    TtlBucket bucket = getBucketContaining(file);
    if (bucket != null) {
      bucket.removeFile(file);
    }
  }