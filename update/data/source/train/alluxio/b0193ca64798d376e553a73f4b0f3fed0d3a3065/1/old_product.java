public InStreamOptions toInStreamOptions() {
    return InStreamOptions.defaults().setReadType(mReadType).setLocationPolicy(mLocationPolicy);
  }