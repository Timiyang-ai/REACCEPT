public void insert(InodeFile file) {
    if (file.getTtl() == Constants.NO_TTL) {
      return;
    }

    TtlBucket bucket = getBucketContaining(file);
    if (bucket == null) {
      long ttlEndTimeMs = file.getCreationTimeMs() + file.getTtl();
      // No bucket contains the file, so a new bucket should be added with an appropriate interval
      // start. Assume the list of buckets have continuous intervals, and the first interval starts
      // at 0, then ttlEndTimeMs should be in number (ttlEndTimeMs / interval) interval, so the
      // start time of this interval should be (ttlEndTimeMs / interval) * interval.
      long interval = TtlBucket.getTtlIntervalMs();
      bucket = new TtlBucket(interval == 0 ? ttlEndTimeMs : ttlEndTimeMs / interval * interval);
      mBucketList.add(bucket);
    }
    bucket.addFile(file);
  }