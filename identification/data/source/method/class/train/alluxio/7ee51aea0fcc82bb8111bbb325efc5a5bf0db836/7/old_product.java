public synchronized List<MasterBlockLocation> getBlockLocations() {
    List<MasterBlockLocation> ret = new ArrayList<MasterBlockLocation>(mWorkerIdToAlias.size());
    for (StorageLevelAlias alias : StorageLevelAlias.values()) {
      for (Map.Entry<Long, Integer> entry : mWorkerIdToAlias.entrySet()) {
        if (alias.getValue() == entry.getValue()) {
          ret.add(new MasterBlockLocation(entry.getKey(), alias.getValue()));
        }
      }
    }
    return ret;
  }