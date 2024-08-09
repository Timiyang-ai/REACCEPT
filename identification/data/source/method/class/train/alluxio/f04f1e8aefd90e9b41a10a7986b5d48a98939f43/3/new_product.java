public void insert(Inode<?> inode) {
    if (inode.getTtl() == Constants.NO_TTL) {
      return;
    }

    TtlBucket bucket = getBucketContaining(inode);
    if (bucket == null) {
      long ttlEndTimeMs = inode.getCreationTimeMs() + inode.getTtl();
      // No bucket contains the inode, so a new bucket should be added with an appropriate interval
      // start. Assume the list of buckets have continuous intervals, and the first interval starts
      // at 0, then ttlEndTimeMs should be in number (ttlEndTimeMs / interval) interval, so the
      // start time of this interval should be (ttlEndTimeMs / interval) * interval.
      long interval = TtlBucket.getTtlIntervalMs();
      bucket = new TtlBucket(interval == 0 ? ttlEndTimeMs : ttlEndTimeMs / interval * interval);
      synchronized (mBucketList) {
        TtlBucket tmpBucket = getBucketContaining(inode);
        if (tmpBucket != null) {
          // The bucket has been inserted in another thread just now, so use the existing bucket.
          bucket = tmpBucket;
        } else {
          mBucketList.add(bucket);
        }
      }
    }
    // TODO(zhouyufa): Consider the concurrent situation that the bucket is expired and processed by
    // the InodeTtlChecker, then adding the inode into the bucket is meaningless since the bucket
    // will not be accessed again. (c.f. ALLUXIO-2821)
    bucket.addInode(inode);
  }