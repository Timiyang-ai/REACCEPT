public InStreamOptions toInStreamOptions() {
    return InStreamOptions.defaults().setReadType(mReadType).setLocationPolicy(mCacheLocationPolicy)
        .setMaxUfsReadConcurrency(mMaxUfsReadConcurrency)
        .setUfsReadLocationPolicy(mUfsReadLocationPolicy);
  }