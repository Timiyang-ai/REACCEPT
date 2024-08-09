@Override
  @Nullable
  public WorkerNetAddress getWorker(GetWorkerOptions options) {
    Set<WorkerNetAddress> eligibleAddresses = new HashSet<>();
    for (BlockWorkerInfo info : options.getBlockWorkerInfos()) {
      eligibleAddresses.add(info.getNetAddress());
    }

    WorkerNetAddress address = mBlockLocationCache.get(options.getBlockInfo().getBlockId());
    if (address != null && eligibleAddresses.contains(address)) {
      return address;
    } else {
      address = null;
    }

    if (!mInitialized) {
      mWorkerInfoList = Lists.newArrayList(options.getBlockWorkerInfos());
      Collections.shuffle(mWorkerInfoList);
      mIndex = 0;
      mInitialized = true;
    }

    // at most try all the workers
    for (int i = 0; i < mWorkerInfoList.size(); i++) {
      WorkerNetAddress candidate = mWorkerInfoList.get(mIndex).getNetAddress();
      BlockWorkerInfo workerInfo = findBlockWorkerInfo(options.getBlockWorkerInfos(), candidate);
      mIndex = (mIndex + 1) % mWorkerInfoList.size();
      if (workerInfo != null
          && workerInfo.getCapacityBytes() >= options.getBlockInfo().getLength()
          && eligibleAddresses.contains(candidate)) {
        address = candidate;
        break;
      }
    }
    mBlockLocationCache.put(options.getBlockInfo().getBlockId(), address);
    return address;
  }