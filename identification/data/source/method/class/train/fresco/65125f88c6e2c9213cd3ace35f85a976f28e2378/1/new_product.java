public V get(int size) {
    ensurePoolSizeInvariant();

    int bucketedSize = getBucketedSize(size);
    int sizeInBytes = -1;

    synchronized (this) {
      Bucket<V> bucket = getBucket(bucketedSize);

      if (bucket != null) {
        // find an existing value that we can reuse
        V value = bucket.get();
        if (value != null) {
          Preconditions.checkState(mInUseValues.add(value));

          // It is possible that we got a 'larger' value than we asked for.
          // lets recompute size in bytes here
          bucketedSize = getBucketedSizeForValue(value);
          sizeInBytes = getSizeInBytes(bucketedSize);
          mUsed.increment(sizeInBytes);
          mFree.decrement(sizeInBytes);
          mPoolStatsTracker.onValueReuse(sizeInBytes);
          logStats();
          if (FLog.isLoggable(FLog.VERBOSE)) {
            FLog.v(
                TAG,
                "get (reuse) (object, size) = (%x, %s)",
                System.identityHashCode(value),
                bucketedSize);
          }
          return value;
        }
        // fall through
      }
      // check to see if we can allocate a value of the given size without exceeding the hard cap
      sizeInBytes = getSizeInBytes(bucketedSize);
      if (!canAllocate(sizeInBytes)) {
        throw new PoolSizeViolationException(
            mPoolParams.maxSizeHardCap,
            mUsed.mNumBytes,
            mFree.mNumBytes,
            sizeInBytes);
      }

      // Optimistically assume that allocation succeeds - if it fails, we need to undo those changes
      mUsed.increment(sizeInBytes);
      if (bucket != null) {
        bucket.incrementInUseCount();
      }
    }

    V value = null;
    try {
      // allocate the value outside the synchronized block, because it can be pretty expensive
      // we could have done the allocation inside the synchronized block,
      // but that would have blocked out other operations on the pool
      value = alloc(bucketedSize);
    } catch (Throwable e) {
      // Assumption we made previously is not valid - allocation failed. We need to fix internal
      // counters.
      synchronized (this) {
        mUsed.decrement(sizeInBytes);
        Bucket<V> bucket = getBucket(bucketedSize);
        if (bucket != null) {
          bucket.decrementInUseCount();
        }
      }
      Throwables.propagateIfPossible(e);
    }

    // NOTE: We checked for hard caps earlier, and then did the alloc above. Now we need to
    // update state - but it is possible that a concurrent thread did a similar operation - with
    // the result being that we're now over the hard cap.
    // We are willing to live with that situation - especially since the trim call below should
    // be able to trim back memory usage.
    synchronized(this) {
      Preconditions.checkState(mInUseValues.add(value));
      // If we're over the pool's max size, try to trim the pool appropriately
      trimToSoftCap();
      mPoolStatsTracker.onAlloc(sizeInBytes);
      logStats();
      if (FLog.isLoggable(FLog.VERBOSE)) {
        FLog.v(
            TAG,
            "get (alloc) (object, size) = (%x, %s)",
            System.identityHashCode(value),
            bucketedSize);
      }
    }

    return value;
  }